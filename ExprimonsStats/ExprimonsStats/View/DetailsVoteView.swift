//
//  DetailsVoteView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 28/08/2022.
//

import SwiftUI
import SwiftPieChart
import Alamofire
import SwiftyJSON

struct DetailsVoteView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
     var vote:Vote
    @State var count:Int=0
    @State var nbVotesStat:[Double]=[]
    @State var labelStats:[String]=[]
    @State var testVoteisfirstVoted:Bool=false
    
    func refreshAllOptionsVote(idCommunity:Int){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        let params: Parameters = [
            "idVote": vote.idVote ?? 0,
        ]
        
        AF.request("https://www.titan-photography.com/vote/voteInfo", method: .post,parameters: params, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    var data = JSON(json)
                    data=data[0]["voteOptions"]
                    nbVotesStat=[]
                    labelStats=[]
                    count = 0
                    for i in 0 ... data.count-1{
                        
                        
                        
                        
                        labelStats.append(data[i]["label"].string!)
                        nbVotesStat.append(Double(data[i]["nbChoice"].int!))
                        
                        if(nbVotesStat[i] != 0){
                            testVoteisfirstVoted=true
                        }
                       
                        
                    }
                    print(nbVotesStat)
                    
                    
                } catch {
                    print("Error: Trying to convert JSON data to string")
                    return
                }
            case .failure(let error):
                
                if(response.response?.statusCode == 406 || response.response?.statusCode==404){
                    alert=true
                    
                    
                    
                    
                }
                else{
                    print("mais")
                }
                
            }
        }
        
    }
    
    var body: some View {
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:100){
                    
                    Text(vote.title ?? "Loading")
                        .font(.system(size: 48))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    VStack(spacing:20){
                        Spacer( )
                        Text(vote.body ?? "Loading")
                            .font(.system(size: 18))
                        
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                            
                        //if(nbVotesStat.isEmpty){}
                         if(testVoteisfirstVoted==false){
                            Text("Aucun vote n'a été enregistré pour le moment")
                             VStack(){
                                 ForEach(Array(labelStats.enumerated()),id:\.offset){index,labelOptionVote in
                                        
                                     HStack(alignment: .center){
                                        
                                         Text("Options\(index+1)")
                                         Text(labelOptionVote ?? "Loading")
                                         Spacer()
                                         
                                     }.padding(.horizontal, 50.0).frame(height: 100.0).background(Color.white).cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/).onAppear(perform: {
                                         count+=1
                                     })
                                 }
                             }
                             
                             Spacer()
                            
                        }else{
                            VStack{
                                
                                    PieChartView(
                                        values: nbVotesStat,
                                        names: labelStats,
                                        formatter: {value in String(format: "%.2f", value)
                                        },
                                        backgroundColor: Color.black
                                        
                                        
                                        
                                    )
                                    
                                    .padding(.all, 5.0)
                                    .cornerRadius(25)
                                
                                
                                
                            }
                            .frame(width: 500, height: 550)
                            .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.black/*@END_MENU_TOKEN@*/)
                            .cornerRadius(25)
                        }
                        
                    }
                    .frame(width: 600, height: 650, alignment: Alignment.center)
                    .background(Color.white)
                    .cornerRadius(25)
         
                }
            ).onAppear(perform: {
                refreshAllOptionsVote(idCommunity: UserDefaults.standard.integer(forKey: "idCommunity"))
            })
    }
}

struct DetailsVoteView_Previews: PreviewProvider {
    @State static var isConnected=true
    @State static var vote=Vote(idVote: 0, title: "titre du vote", body: "Corps du vote", nbChoice: 2, important:1 , idAdmin: 2, voteBegins: "2022-02-12", voteEnds: "2022-02-19", idCommunity: 1)
    static var previews: some View {
        DetailsVoteView(isConnected: $isConnected, vote: vote)
    }
}
