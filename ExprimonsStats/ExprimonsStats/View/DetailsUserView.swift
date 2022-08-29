//
//  DetailsUserView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 20/05/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON

struct DetailsUserView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
    @Binding var user:User
    @State var nbPost:Int=0
    @State var nbCommentaire:Int=0
    
    func refreshNbPost(){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/user/nbPosts/\(user.idUser ?? 0)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    print(data)
                    
                    
                    
                    nbPost=data[0]["nbPosts"].int!
                    
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
    
    func refreshNbCommentaire(){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/user/nbComments/\(user.idUser ?? 0)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    print(data)
                    nbCommentaire=data[0]["nbComments"].int!
                    
                    
                    
                    
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
                    
                    Text((user.lastName ?? "Loading") + " " + (user.firstName ?? ""))
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    HStack{
                        VStack{
                            Text("Points")
                                .font(.system(size: 24))
                            
                            Text("\(user.points ?? 0)")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                             
                        }
                        .frame(width: 250.0, height: 250.0)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        
                        
                    }
                    
                    HStack(spacing:150){
                        VStack{
                            Text("Posts")
                                .font(.system(size: 24))
                            
                            Text("\(nbPost)")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                             
                        }
                        .frame(width: 250.0, height: 250.0)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        VStack{
                            Text("Commentaires")
                                .font(.system(size: 24))
                            
                            Text("\(nbCommentaire)")
                                .font(.system(size: 36))
                                .frame(width: 150.0, height: 150.0)
                                .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                                .cornerRadius(/*@START_MENU_TOKEN@*/100.0/*@END_MENU_TOKEN@*/)
                             
                        }
                        .frame(width: 250.0, height: 250.0)
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    }
                    
                    
                    
                }
                
            ).onAppear(perform: {
                refreshNbPost()
                refreshNbCommentaire()
                
            })
    }
}

struct DetailsUserView_Previews: PreviewProvider {
    @State static var userdetailTest=1
    @State static var isConnected=true
    @State static var user = User(idUser: 1, firstName: "Théo", lastName: "Torres", birthDate: "2001-03-14", gender: "Homme", areaCode: "77720", email: "ttorresdacosta@myges.fr", points: 123)
    static var previews: some View {
        DetailsUserView(isConnected:$isConnected,user: $user)
    }
}
