//
//  Vote.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 14/05/2022.
//

import Foundation

class Vote:ObservableObject,Identifiable{
    let id=UUID()
    @Published var idVote:Int?
    @Published var title:String?
    @Published var body:String?
    @Published var nbChoice:Int?
    @Published var important:Int?
    
    @Published var idAdmin:Int?
    @Published var voteBegins:String?
    @Published var voteEnds:String?
    @Published var idCommunity:Int?
    init(idVote:Int,
         title:String,
         body:String,
         nbChoice:Int,
         important:Int?,
         idAdmin:Int,voteBegins:String,voteEnds:String,idCommunity:Int){
        self.idVote=idVote
        self.title=title
        self.body=body
        self.nbChoice=nbChoice
        self.important=important
        
        self.idAdmin=idAdmin
        self.voteBegins=voteBegins
        self.voteEnds=voteEnds
        self.idCommunity=idCommunity
    }
}




