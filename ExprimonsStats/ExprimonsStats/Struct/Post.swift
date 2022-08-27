//
//  Post.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 20/05/2022.
//

import Foundation

class Post:ObservableObject,Identifiable{
    let id=UUID()
    @Published var idPost:Int?
    @Published var title:String?
    @Published var body:String?
    @Published var date:String?
    @Published var time:String?
    @Published var likes:Int?
    @Published var dislikes:Int?
    @Published var idCommunity:Int?
    @Published var idUser:Int?
    @Published var idAdmin:Int?
    @Published var reported:Int?
    
    
    
    
    init(idPost:Int,
         title:String,
         body:String,
         date:String,
         time:String,
         likes:Int,
         dislikes:Int,
         idCommunity:Int,
         idUser:Int,
         idAdmin:Int,
         reported:Int){
        self.idPost=idPost
        self.title=title
        self.body=body
        self.date=date
        self.time=time
        self.likes=likes
        self.dislikes=dislikes
        self.idCommunity=idCommunity
        self.idUser=idUser
        self.idAdmin=idAdmin
        self.reported=reported
    }
}
