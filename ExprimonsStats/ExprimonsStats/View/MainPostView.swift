//
//  MainPostView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import SwiftUI
import Alamofire
import SwiftyJSON

struct MainPostView: View {
    @State var alert:Bool=false
    @Binding var isConnected: Bool
    @State private var showingSheet = false
    @State var allPosts:[Post]=[]
    @State var lastPosts:[Post]=[]
    
    func refreshAllPosts(idCommunity:Int){
        
        
        let headers: HTTPHeaders = [
            //"token":UserDefaults.standard.string(forKey: "token")!
            "Content-Type":"application/json",
            "token":UserDefaults.standard.string(forKey: "token") ?? ""
        ]
        
        AF.request("https://www.titan-photography.com/post/all/\(idCommunity)", method: .get, encoding: JSONEncoding.default, headers: headers).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                
            case .success(let json):
                
                do {
                    let data = JSON(json)
                    
                    let currentDate = Date()
                    let calendar = Calendar.current
                    let year = calendar.component(.year, from: currentDate)
                    let month = calendar.component(.month, from: currentDate)
                    let day=calendar.component(.day, from: currentDate)
                    
                    allPosts=[]
                    lastPosts=[]
                    
                    
                    for i in 0 ... data.count-1{
                        
                        let date:String=data[i]["time"].string!
                        let dateSplitted = date.split(separator: "-")
                        
                        let newPost=Post(
                            idPost: data[i]["idPost"].int!,
                            title: data[i]["title"].string!,
                            body: data[i]["body"].string!,
                            date: data[i]["date"].string!,
                            time: data[i]["time"].string!,
                            likes: data[i]["likes"].int!,
                            dislikes: data[i]["dislikes"].int!,
                            idCommunity: data[i]["idCommunity"].int!,
                            idUser: data[i]["idUser"].int ?? 0,
                            idAdmin: data[i]["idAdmin"].int ?? 0,
                            reported: data[i]["reported"].int!)
                        allPosts.append(newPost)
                        if(Int(dateSplitted[0]) ?? 0==year){
                            
                            if(Int(dateSplitted[1]) ?? 0==month){
                                
                                if(Int(dateSplitted[2]) ?? 0>=day-7){
                                    lastPosts.append(newPost)
                                    
                                }
                            }
                            else if(Int(dateSplitted[1]) ?? 0>month){
                                lastPosts.append(newPost)
                            }
                        }
                        else if(Int(dateSplitted[0]) ?? 0>year){
                            lastPosts.append(newPost)
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
                    Text("Post")
                        .font(.system(size: 48))
                        .foregroundColor(Color.white)
                        .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                        .background(Color.darkColor)
                        .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    VStack(spacing:30){
                        Text("Post en cours")
                            .font(.system(size: 36))
                            .foregroundColor(Color.white)
                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                            .background(Color.darkColor)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        
                        ScrollView(.horizontal){
                            HStack(spacing:20){
                                ForEach(allPosts,id:\.id) {curPost in
                                    VStack{
                                        Text(curPost.title ?? "Loading")
                                            .font(.system(size: 36))
                                            .foregroundColor(Color.white)
                                            .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                                            .background(Color.darkColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                        
                                        HStack{
                                            VStack{
                                            Text("\(curPost.likes ?? 0)")
                                                .font(.system(size: 36))
                                                
                                            Image(systemName: "hand.thumbsup.circle")
                                                    .font(.system(size: 50))
                                            }
                                            .foregroundColor(Color.black)
                                            .frame(width: 110.0, height: 140.0)
                                            .background(Color.lightColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                            Spacer()
                                            VStack{
                                            Text("\(curPost.dislikes ?? 0)")
                                                .font(.system(size: 36))
                                                
                                            Image(systemName: "note.text.badge.plus")
                                                    .font(.system(size: 50))
                                            }
                                            .foregroundColor(Color.black)
                                            .frame(width: 110.0, height: 140.0)
                                            .background(Color.lightColor)
                                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                                            
                                            
                                        }
                                        .padding(.horizontal, 15.0)
                                        .frame(width: 280.0, height: 150.0)
                                        .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                                        .cornerRadius(25)
                                        NavigationLink(destination: DetailsPostView(isConnected: $isConnected, post: curPost)){
                                            
                                        
                                        
                                            Text("Details")
                                                .font(.system(size:36))
                                                .foregroundColor(Color.black)
                                        
                                        .frame(width: 200.0, height: 60)
                                        .background(Color.ligthColor2)
                                        .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                        }
                                        
                                        
                                    }
                                    .frame(width: 300.0, height: 380.0)
                                    .background(Color.lightColor)
                                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                                    
                                }
                            }
                        }
                        .frame(height: nil)
                        
                    }
                    .frame(width: 700, height: 530)
                    .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color.white/*@END_MENU_TOKEN@*/)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    
                    NavigationLink(destination: ListOfPostView(isConnected: $isConnected, posts: allPosts)) {
                        Text("Voir l'historique")
                            .font(.system(size:36))
                            .foregroundColor(Color.black)
                    }
                    .frame(width: 400, height: 60)
                    .background(Color.ligthColor2)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    .sheet(isPresented: $showingSheet) {
                        SheetPostView()
                                
                                
                        
                    }
                }
            
                    
                
            ).onAppear(perform: {refreshAllPosts(idCommunity: UserDefaults.standard.integer(forKey: "idCommunity"))
            })
    }
}

struct MainPostView_Previews: PreviewProvider {
    @State static var isConnected=true
    static var previews: some View {
        MainPostView(isConnected: $isConnected)
    }
}
