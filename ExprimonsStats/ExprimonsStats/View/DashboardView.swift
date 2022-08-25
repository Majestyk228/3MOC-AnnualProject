//
//  DashBoard.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 17/04/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON


@available(iOS 15.0, *)
struct DashboardView: View {
    @State var alert:Bool=false
    @State var dashboardStats:DashboardStat=DashboardStat(nbUsers: -1, totalPointsCommunity: -1, nbPost: -1, nbVote: -1)
    @State var CommunityTitle:String?
    @Binding var isConnected: Bool
    func refreshDashboardStat(idCommunity:Int){
        UserDefaults.standard.set("", forKey: "token")
        let params: Parameters = [
            "idCommunity": idCommunity,
        ]
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/community/stats", method: .post, parameters: params, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    print(data)
                    dashboardStats=DashboardStat(nbUsers: data[0]["nbUsers"].int!, totalPointsCommunity: data[0]["totalPointsCommunity"].int!, nbPost: data[0]["nbPosts"].int!, nbVote: data[0]["nbVotes"].int!)
                    
                    
                    //nbUsers=data[0]["nbUsers"].int ?? 37123
                    
                    
                } catch {
                    print("Error: Trying to convert JSON data to string")
                    return
                }
            case .failure(let error):
                if(response.response?.statusCode == 406 || response.response?.statusCode==404){
                    alert=true
                    
                    
                    
                }
                
            }
        }
        
    }
    func refreshCommunityTitle(idCommunity:Int){
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? "nil"
        ]
        AF.request("https://www.titan-photography.com/community/\(UserDefaults.standard.integer(forKey: "idCommunity"))", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    
                    CommunityTitle=data[0]["label"].string
                    
                    //nbUsers=data[0]["nbUsers"].int ?? 37123
                    
                    
                } catch {
                    print("Error: Trying to convert JSON data to string")
                    return
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    
    var body: some View {
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:150){
                    Text(CommunityTitle ?? "Load...")
                        .font(.system(size: 48))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    
                    HStack(spacing: 150){
                        VStack{
                            Text("Utilisateurs")
                                .font(.system(size: 24))
                            if(dashboardStats.nbUsers == -1){
                                Text(String("Load.."))
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }else{
                                Text(String(dashboardStats.nbUsers!))
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }
                            
                        }
                        .frame(width: 250.0, height: 250.0)
                        .onAppear(perform: {
                            
                        })
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        VStack{
                            Text("Points Totals")
                                .font(.system(size: 24))
                            if(dashboardStats.totalPointsCommunity == -1 ){
                                Text("Load...")
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }else{
                                Text(String(dashboardStats.totalPointsCommunity!))
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }
                            
                        }
                        .frame(width: 250.0, height: 250.0)
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                    }
                    .frame(width: /*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/, height: /*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                    HStack(spacing:150){
                        VStack{
                            Text("Posts")
                                .font(.system(size: 24))
                            if(dashboardStats.nbPost == -1){
                                Text("Load...")
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }else{
                                Text(String(dashboardStats.nbPost!))
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }
                            
                        }
                        .frame(width: 250.0, height: 250.0)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        VStack{
                            Text("Votes")
                                .font(.system(size: 24))
                            if(dashboardStats.nbVote == -1){
                                Text(String("Load..."))
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }else{
                                Text(String(dashboardStats.nbVote!))
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }
                            
                        }
                        .frame(width: 250.0, height: 250.0)
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        .onAppear{
                            
                        }
                    }
                    
                    
                    
                }
                
            ).onAppear(perform: {refreshDashboardStat(idCommunity: UserDefaults.standard.integer(forKey: "idCommunity"))
                refreshCommunityTitle(idCommunity: UserDefaults.standard.integer(forKey: "idCommunity"))
            })
            .alert(isPresented: $alert){
                Alert(title: Text("Erreur"),
                      message: Text("Votre session a expiré veuillez vous reconnecter"),
                      dismissButton: .default(Text("Got it!")){
                    if let bundleID = Bundle.main.bundleIdentifier {
                        UserDefaults.standard.removePersistentDomain(forName: bundleID)
                    }
                    isConnected=false
                }
                )
            }
    }
}

@available(iOS 15.0, *)
struct DashBoard_Previews: PreviewProvider {
    @State static var isConnected=true
    
    static var previews: some View {
        DashboardView(isConnected: $isConnected)
    }
}

