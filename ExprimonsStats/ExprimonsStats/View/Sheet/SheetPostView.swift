//
//  SheetPostView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 20/05/2022.
//

import SwiftUI

struct SheetPostView: View {
    
    var body: some View {
        VStack(spacing:100){
            Text("Historique des Post")
                .font(.system(size: 36))
                .foregroundColor(Color.white)
                .padding(EdgeInsets(top: 10, leading: 40, bottom: 10, trailing: 40) )
                .background(Color.darkColor)
                .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
            ScrollView{
                VStack(spacing:20){
                    /*
                    ForEach(titlePost){title in
                        HStack{
                            Image(systemName: "tray.and.arrow.down")
                                .font(.system(size: 24))
                            Spacer()
                            Text(title.titlePost).font(.system(size: 36))
                            Spacer()
                            Image(systemName: "chevron.right")
                                .font(.system(size: 24))
                        }.padding(.horizontal, 50.0).frame(height: 100.0).background(Color.normalColor).cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                    }
                     */
                }
                .padding(.horizontal, 25.0)
            }
        }
        .padding(.top, 50.0)
    }
}

struct SheetPostView_Previews: PreviewProvider {
    static var previews: some View {
        SheetPostView()
    }
}
