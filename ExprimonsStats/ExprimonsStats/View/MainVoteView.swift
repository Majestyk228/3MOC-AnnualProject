//
//  MainVoteView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 12/05/2022.
//
import Foundation
import SwiftUI
import Alamofire
import SwiftyJSON

struct MainVoteView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
    @State private var showingSheet = false
    @State var allVotes:[Vote]=[]
    @State var allCurrentVotes:[Vote]=[]
    func refreshAllVotes(idCommunity:Int){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/vote/voteList/\(idCommunity)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    
                    let currentDate = Date()
                    let calendar = Calendar.current
                    let year = calendar.component(.year, from: currentDate)
                    let month = calendar.component(.month, from: currentDate)
                    let day=calendar.component(.day, from: currentDate)
                    
                    allVotes=[]
                    allCurrentVotes=[]
                    
                    for i in 0 ... data.count-1{
                        
                        let date:String=data[i]["voteEnds"].string!
                        let dateSplitted = date.split(separator: "-")
                        
                        let newVote=Vote(
                            idVote: data[i]["idVote"].int!,
                            title: data[i]["title"].string!,
                            body: data[i]["body"].string!,
                            nbChoice: data[i]["nbChoices"].int!,
                            important: data[i]["important"].int!,
                            idAdmin: data[i]["idAdmin"].int!,
                            voteBegins: data[i]["voteBegins"].string!,
                            voteEnds: data[i]["voteEnds"].string!,
                            idCommunity: data[i]["idCommunity"].int!)
                        allVotes.append(newVote)
                        if(Int(dateSplitted[0]) ?? 0 == year){
                            
                            if(Int(dateSplitted[1]) ?? 0 == month){
                                
                                if(Int(dateSplitted[2]) ?? 0>=day){
                                    allCurrentVotes.append(newVote)
                                    
                                }
                            }
                            else if(Int(dateSplitted[1]) ?? 0 > month){
                                allCurrentVotes.append(newVote)
                            }
                        }
                        else if(Int(dateSplitted[0]) ?? 0 > year){
                            allCurrentVotes.append(newVote)
                        }
                        
                    }
                    
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
            .edgesIgnoringSafeArea(.all)// Ignore just for the color
            .overlay(
                VStack(spacing:100){
                    Text("Vote")
                        .font(.system(size: 48))
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    VStack(spacing:30){
                        Text("Votes en cours")
                            .font(.system(size: 36))
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        
                        ScrollView(.horizontal){
                            
                            HStack(spacing:20){
                                ForEach(allCurrentVotes,id: \.id) {vote in
                                    NavigationLink(destination: DetailsVoteView(isConnected: $isConnected, vote: vote)){
                                        
                                    
                                    VStack{
                                        Text(vote.title ?? "Loading")
                                            .font(.system(size: 36))
                                            .foregroundColor(Color.white)
                                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                                            .background(Color.darkColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                        
                                        VStack{
                                            Text("2")
                                                .font(.system(size: 36))
                                                .foregroundColor(Color.white)
                                                .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                                                .background(Color.darkColor)
                                                .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                            
                                            Text(vote.body ?? "Loading")
                                                .font(.system(size: 24))
                                            
                                        }
                                        .frame(width: 280.0, height: 150.0)
                                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                                        .cornerRadius(25)
                                        
                                        
                                        
                                        
                                        
                                    }
                                    .frame(width: 300.0, height: 380.0)
                                    .background(Color.lightColor)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                    }
                                }
                            }
                             
                        }
                        .frame(height: nil)
                        
                    }
                    .frame(width: 700, height: 530)
                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    
                    NavigationLink(destination: ListOfVoteView(isConnected: $isConnected, votes: allVotes)) {
                        Text("Voir l'historique")
                            .font(.system(size:36))
                            .foregroundColor(Color.black)
                    }
                    .frame(width: 400, height: 60)
                    .background(Color.ligthColor2)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    .sheet(isPresented: $showingSheet) {
                        SheetVoteView()
                                
                                
                        
                    }
                }
            ).onAppear(perform: {refreshAllVotes(idCommunity: UserDefaults.standard.integer(forKey: "idCommunity"))
            })
   
    }
}

struct MainVoteView_Previews: PreviewProvider {
    @State static var isConnected=true
    
    static var previews: some View {
        MainVoteView(isConnected: $isConnected)
    }
}
