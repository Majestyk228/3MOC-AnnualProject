//
//  DashBoard.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 17/04/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON


struct DashboardView: View {
    @State var dashboardStats:DashboardStat=DashboardStat(nbUsers: -1, totalPointsCommunity: "-1", nbPost: -1, nbVote: -1)
    @State var CommunityTitle:String?
    func refreshDashboardStat(idCommunity:Int){
        
        let params: Parameters = [
            "idCommunity": idCommunity,
        ]
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token")!
        ]
        
        AF.request("https://www.titan-photography.com/community/stats", method: .post, parameters: params, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    
                    dashboardStats=DashboardStat(nbUsers: data[0]["nbUsers"].int!, totalPointsCommunity: data[0]["totalPointsCommunity"].string!, nbPost: data[0]["nbPost"].int!, nbVote: data[0]["nbVote"].int!)
                    
                    
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
    func refreshCommunityTitle(idCommunity:Int){
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token")!
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
                        .font(.system(size: 36))
                    
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
                            if(dashboardStats.totalPointsCommunity=="-1"){
                                Text("Load...")
                                    .font(.system(size: 36))
                                    .frame(width: 150.0, height: 150.0)
                                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                            }else{
                                Text(dashboardStats.totalPointsCommunity!)
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
    }
}

struct DashBoard_Previews: PreviewProvider {
    
    
    static var previews: some View {
        DashboardView()
    }
}

