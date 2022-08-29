//
//  VoteOptions.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 28/08/2022.
//

import Foundation

class OptionsVote:ObservableObject,Identifiable{
    let id=UUID()
    @Published var label:String?
    @Published var idVoteOptions:Int?
    @Published var nbChoice:Int?
    
    init(label:String,
         idVoteOptions:Int,
         nbChoice:Int
         ){
        self.label=label
        self.idVoteOptions=idVoteOptions
        self.nbChoice=nbChoice
        
    }
}
