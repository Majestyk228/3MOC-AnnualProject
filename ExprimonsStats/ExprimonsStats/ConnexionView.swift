//
//  ContentView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 01/04/2022.
//

import SwiftUI


struct ConnexionView: View {
    
    
    
    @Binding var userId : Int?
    //variable de test
    struct User{
        var testId:Int
        var userName:String
        var Password:String
    }
    let Test=User(testId:1,userName:"root",Password:"root")
    
    var body: some View {
        Color.green
            .ignoresSafeArea() // Ignore just for the color
            .overlay(
                
                VStack(spacing: 20){
                    Image("Icon")
                        .resizable(resizingMode: .stretch)
                        .frame(width: 200.0, height: 200.0)
                    VStack {
                        //Connectez-vous !
                        
                        Form{
                            Text("Connectez-Vous")
                                .font(.largeTitle)
                                .multilineTextAlignment(.center)
                                .frame(maxWidth:.infinity)
                            
                            Section{
                                
                                TextField(/*@START_MENU_TOKEN@*/"Placeholder"/*@END_MENU_TOKEN@*/, text: /*@START_MENU_TOKEN@*//*@PLACEHOLDER=Value@*/.constant("")/*@END_MENU_TOKEN@*/)
                                
                                SecureField("Enter a password", text:/*@START_MENU_TOKEN@*//*@PLACEHOLDER=Value@*/.constant("")/*@END_MENU_TOKEN@*/)
                                    .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                            }
                            Button(action: {
                                if(1==1){
                                    //do condition if user and password is good and if is good go to dashboard
                                }
                                print(Test)
                                userId=1
                            }) {
                                /*@START_MENU_TOKEN@*/Text("Button")/*@END_MENU_TOKEN@*/
                            }
                        }
                        
                    }
                    
                    .frame(width: 400.0, height: 400.0)
                    
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    
                    
                }
            )
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ConnexionView(userId:.constant(nil))
        }
    }
}
