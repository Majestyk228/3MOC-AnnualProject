//
//  DashBoard.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 17/04/2022.
//

import SwiftUI

struct DashBoard: View {
   @Binding var userId : Int?
   
    var body: some View {
        NavigationView{
            VStack{
                
                Button(action: {
                    
                    print(userId ?? "novalue")
                    userId=1
                }) {
                    /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                }
                
                
            }
        }
        .navigationViewStyle(StackNavigationViewStyle())
        
        
    }
}

struct DashBoard_Previews: PreviewProvider {
   
    static var previews: some View {
        
        DashBoard(userId:.constant(1))
    }
}
