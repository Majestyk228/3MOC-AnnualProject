//
//  MainUsersView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON
struct MainUsersView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
    
    @State var allUsers:[User]=[]
    @State var bestUsers:[User]=[User(idUser: 0, firstName: "Loading", lastName: "Loading", birthDate: "Loading", gender: "Loading", areaCode: "Loading", email: "Loading", points: 0),User(idUser: 0, firstName: "Loading", lastName: "Loading", birthDate: "Loading", gender: "Loading", areaCode: "Loading", email: "Loading", points: 0),User(idUser: 0, firstName: "Loading", lastName: "Loading", birthDate: "Loading", gender: "Loading", areaCode: "Loading", email: "Loading", points: 0)]
    func refreshAllUsers(idCommunity:Int){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/user/all/\(idCommunity)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    print(data)
                    allUsers=[]
                    
                    
                    for i in 0 ... data.count-1{
                        
                       
                        
                        let newUser=User(
                            idUser: data[i]["idUser"].int!,
                            firstName: data[i]["firstName"].string!,
                            lastName: data[i]["lastName"].string!,
                            birthDate: data[i]["birthDate"].string!,
                            gender: data[i]["gender"].string!,
                            areaCode: data[i]["areaCode"].string!,
                            email: data[i]["email"].string!,
                            points: data[i]["points"].int!
                            )
                        allUsers.append(newUser)
                        
                        
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
                
                
                VStack(spacing:50){
                    Text("Utilisateurs")
                        .font(.system(size: 48))
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    VStack(spacing:30){
                        Text("Meilleurs Utilisateurs")
                            .font(.system(size: 36))
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        HStack{
                            
                            NavigationLink(destination:DetailsUserView(isConnected:$isConnected)) {
                                VStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 100))
                                    
                                    if(allUsers.isEmpty){
                                        Text(bestUsers[0].firstName ?? "Loading")
                                            .font(.system(size:36))
                                            .foregroundColor(Color.black)
                                    }
                                    else{
                                        Text(allUsers[0].firstName ?? "Loading")
                                            .font(.system(size:36))
                                            .foregroundColor(Color.black)
                                    }
                                }
                                
                            }
                            .frame(width: 200, height: 250)
                            .background(Color.lightColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                             
                            
                            NavigationLink(destination:DetailsUserView(isConnected:$isConnected)) {
                                VStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 100))
                                    
                                    if(allUsers.isEmpty){
                                        Text(bestUsers[1].firstName ?? "Loading")
                                            .font(.system(size:36))
                                            .foregroundColor(Color.black)
                                    }
                                    else{
                                        Text(allUsers[1].firstName ?? "Loading")
                                            .font(.system(size:36))
                                            .foregroundColor(Color.black)
                                    }
                                }
                                
                            }
                            .frame(width: 200, height: 250)
                            .background(Color.lightColor)
                            .cornerRadius(50.0)
                            
                            
                            NavigationLink(destination:DetailsUserView(isConnected:$isConnected)) {
                                VStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 100))
                                    if(allUsers.isEmpty){
                                        Text(bestUsers[2].firstName ?? "Loading")
                                            .font(.system(size:36))
                                            .foregroundColor(Color.black)
                                    }
                                    else{
                                        Text(allUsers[2].firstName ?? "Loading")
                                            .font(.system(size:36))
                                            .foregroundColor(Color.black)
                                    }
                                   
                                }
                                
                            }
                            .frame(width: 200, height: 250)
                            .background(Color.lightColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                            
                            
                            
                            
                        }
                        
                        
                 
                    }
                    .frame(width: 700, height: 400.0)
                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    Text("Liste des Utilisateurs")
                        .font(.system(size: 48))
                    ScrollView{
                        VStack(spacing:10){
                            
                            
                            ForEach(allUsers,id: \.id){user in
                                HStack{
                                    Image(systemName: "person.fill")
                                        .font(.system(size: 52))
                                    Spacer()
                                    Text(user.firstName ?? "Loading").font(.system(size: 36))
                                    Spacer()
                                    NavigationLink(destination:DetailsUserView(isConnected:$isConnected)) {
                                    Image(systemName: "chevron.right")
                                        .font(.system(size: 52))
                                    }
                                }.padding(.horizontal, 50.0).frame(height: 100.0).background(Color.white).cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                            }
                             
                        }
                        .padding(.horizontal, 25.0)
                    }
                
                
                }
                    
                
                
            ).onAppear(perform: {refreshAllUsers(idCommunity: UserDefaults.standard.integer(forKey: "idCommunity"))
            })
    }
}

struct MainUsersView_Previews: PreviewProvider {
    @State static var isConnected=true
    static var previews: some View {
        MainUsersView(isConnected: $isConnected)
    }
}
