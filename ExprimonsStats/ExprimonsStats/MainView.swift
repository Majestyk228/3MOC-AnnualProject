//
//  MainView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 26/04/2022.
//

import SwiftUI

struct MainView: View {
    @State private var userId: Int? = nil ;
    
    var body: some View {
        if(userId == nil){
            ConnexionView(userId:self.$userId)
        }
        else{
            DashBoard(userId: self.$userId)
        }
        
    }
}

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
