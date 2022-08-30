//
//  Comment.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 30/08/2022.
//

import Foundation

class Comment:ObservableObject,Identifiable{
    let id=UUID()
    @Published var body:String?
    @Published var likes:Int?
    @Published var dislikes:Int?
    @Published var idUser:Int?
    @Published var anonymous:Int?
    
    init(body:String,
         likes:Int,
         dislikes:Int,
         idUser:Int,
         anonymous:Int
         ){
        self.body=body
        self.likes=likes
        self.dislikes=dislikes
        self.idUser=idUser
        self.anonymous=anonymous
        
        
    }
}
