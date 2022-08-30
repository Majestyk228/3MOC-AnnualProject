//
//  CommentView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 30/08/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON

struct CommentView: View {
    @State var alert:Bool=false
    var postTitle:String
    var postId:Int
    @State var comments:[Comment]=[]
    @State private var allUsers:[User]=[]
    
    func refreshAllComments(){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/comment/all/\(postId)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    print(data)
                    comments=[]
                    
                    
                    for i in 0 ... data.count-1{
                        
                       
                        
                        let newComment=Comment(
                            body: data[i]["body"].string!,
                            likes: data[i]["likes"].int!,
                            dislikes: data[i]["dislikes"].int!,
                            idUser:data[i]["idUser"].int!,
                            anonymous: data[i]["anonymous"].int!)
                            
                        comments.append(newComment)
                        
                        
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
    func refreshAllUsers(idCommunity:Int){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/user/all/points/\(idCommunity)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
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
    var body: some View{
        
        Color.normalColor
            .ignoresSafeArea()
            .overlay(
                VStack(spacing:100){
                    
                    Text(postTitle)
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    
                    ScrollView{
                        VStack(spacing:10){
                            
                            
                            ForEach(comments,id: \.id){comment in
                                
                                HStack{
                                    Image(systemName: "person.fill")
                                        .foregroundColor(Color.black)
                                        .font(.system(size: 30))
                                    if(comment.anonymous == 1 ){
                                        Text("Utilisateur anonyme").font(.system(size: 16))
                                    }else{
                                        if let foo:User = allUsers.first(where: {$0.idUser == comment.idUser}) {
                                           // do something with foo
                                            Text(foo.firstName ?? "Loading")
                                        } else {
                                           Text("Erreur")
                                        }
                                    }
                                    Spacer()
                                    Text(comment.body ?? "Loading").font(.system(size: 16))
                                    Text(String(comment.likes ?? 0)).font(.system(size: 16))
                                    Text(String(comment.dislikes ?? 0)).font(.system(size: 16))
                                    Spacer()
                                    
                                }.padding(.horizontal, 50.0).frame(height: 100.0).background(Color.white).cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                            
                            }
                             
                        }
                        .padding(.horizontal, 25.0)
                    }
                        
                        
                        
                    

                }
                
            ).onAppear(perform: {
                refreshAllComments()
            })
    }
    
}

struct CommentView_Previews: PreviewProvider {
    @State static var postTitle:String="Title"
    @State static var postId:Int=0
    static var previews: some View {
        CommentView(postTitle: postTitle, postId: postId)
    }
}
