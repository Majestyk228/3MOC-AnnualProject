//
//  Vote.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 14/05/2022.
//

import Foundation

struct Vote:Identifiable{
    let id=UUID()
    let title:String
    let bestChoice:String
    let Description:String
    
}

struct ListVote:Identifiable{
    let id = UUID()
    let title:String
}
/*
class Votes:ObservableObject{
    @Published var titleVote:String?
    @Published var title
}
*/
