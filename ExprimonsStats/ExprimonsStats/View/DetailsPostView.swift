//
//  DetailsPostView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 28/08/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON
struct DetailsPostView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
     var post:Post
    @State var nbComment:Int=0
    func refreshNbComment(){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/comment/count/\(post.idPost ?? 0)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    
                    nbComment=data[0]["nbComment"].int!
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
                    
                    Text(post.title ?? "Loading")
                        .font(.system(size: 36))
                    
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                     
                    
                    
                        VStack{
                            HStack(){
                                Text("Corps du post :")
                                    .font(.system(size: 24))
                                Text(post.body ?? "Loading").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            HStack(){
                                Text("Date :")
                                    .font(.system(size: 24))
                                Text(post.date ?? "Loading").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            HStack(){
                                Text("Like :")
                                    .font(.system(size: 24))
                                Text("\(post.likes ?? 0)").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            HStack(){
                                Text("Dislike :")
                                    .font(.system(size: 24))
                                Text("\(post.dislikes ?? 0)").font(.system(size: 24))
                            }.frame(maxWidth:.infinity,alignment: .leading).padding(.all,10)
                            
                            NavigationLink(destination:CommentView(postTitle: post.title ?? "Failed to load", postId: post.idPost ?? 0)){
                                Text("\(nbComment) Commentaires")
                            }.frame(width: 400, height: 60)
                                .background(Color.ligthColor2)
                                .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                             
                        }
                        .frame(width: 650, height: 400)
                        
                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                        .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                        
                        
                        
                    

                }
                
            ).onAppear(perform: {
                refreshNbComment()
            })
    }
}

struct DetailsPostView_Previews: PreviewProvider {
    @State static var post:Post = Post(idPost: 1, title: "Titre du post", body: "Corps du post", date: "2022-02-13", time: "00-00-00", likes: 2, dislikes: 4, idCommunity: 2, idUser: 2, idAdmin: 2, reported: 2)
    @State static var isConnected:Bool=true
    static var previews: some View {
        DetailsPostView(isConnected:$isConnected , post: post)
    }
}
