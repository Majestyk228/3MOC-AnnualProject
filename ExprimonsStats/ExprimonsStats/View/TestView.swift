//
//  TestView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 28/05/2022.
//

import SwiftUI

struct TestView: View {
    @StateObject var viewModel = ViewModel()
    var body: some View {
        NavigationView{
                    List {
                        ForEach(viewModel.admins, id: \.self) { admin in
                            HStack{
                                
                                
                                Text(admin.firstName)
                                    .bold()
                            }
                        }
                    }
                    .navigationTitle("Courses")
                    .onAppear{
                        viewModel.fetch()
                    }
                }
        .navigationViewStyle(StackNavigationViewStyle())
    }
}

struct TestView_Previews: PreviewProvider {
    static var previews: some View {
        TestView()
    }
}
