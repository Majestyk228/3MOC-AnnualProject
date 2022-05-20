//
//  User.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import Foundation

struct User{
    let userId:Int
    let userMail:String
    let userPassword:String
    let communityId:Int
    let communityTitle:String
}

struct BestUser:Identifiable{
    let id=UUID()
    let userName:String
}
struct ListofUser:Identifiable{
    let id=UUID()
    let userName:String
}
