//
//  User.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 13/05/2022.
//

import Foundation


class User:ObservableObject,Identifiable{
    let id=UUID()
    @Published var idUser:Int?
    @Published var firstName:String?
    @Published var lastName:String?
    @Published var birthDate:String?
    @Published var gender:String?
    @Published var areaCode:String?
    @Published var email:String?
    
    @Published var points:Int?
    
    init(idUser:Int,
         firstName:String,
         lastName:String,
         birthDate:String,
         gender:String,
         areaCode:String,
         email:String,
         points:Int){
        self.idUser=idUser
        self.firstName=firstName
        self.lastName=lastName
        self.birthDate=birthDate
        self.gender=gender
        self.areaCode=areaCode
        self.email=email
        self.points=points
    }
}


