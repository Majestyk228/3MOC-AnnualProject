//
//  Post.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 20/05/2022.
//

import Foundation

struct currentPost:Identifiable{
    let id = UUID()
    let titlePost:String
    let like:Int
    let nbComment:Int
}
struct allPost:Identifiable{
    let id = UUID()
    let titlePost:String
}
