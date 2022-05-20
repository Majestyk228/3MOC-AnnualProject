//
//  ContentView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 01/04/2022.
//

import SwiftUI


struct ConnexionView: View {
    
    
    
    @Binding var  user:User
    //variable de test
    let usersTest=[
    User(userId: 1, userMail: "root@root.fr", userPassword: "root", communityId: 1, communityTitle: "SaintDenis"),
    User(userId: 2, userMail: "Test@test.fr", userPassword: "test", communityId: 3, communityTitle: "funEnterprise"),
    User(userId: 3, userMail: "torresdacosta@myges.fr", userPassword: "Torres", communityId: 2, communityTitle: "ESGI")
    ]
    @State private var email:String = ""
    @State private var password:String = ""
    
    
    var body: some View {
        Color.normalColor
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
                                
                                TextField(/*@START_MENU_TOKEN@*/"Email"/*@END_MENU_TOKEN@*/, text: $email)
                                    .autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                                    
                                    
                                
                                SecureField("Mot de passe", text:$password)
                                    .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                            }
                            Button(action: {
                                
                                for test in usersTest{
                                    
                                    if(test.userMail==email){
                                        
                                        if(test.userPassword==password){
                                            
                                            user = test
                                        }
                                    }
                                }
                                
                                
                                   
                            }) {
                                HStack{
                                    Spacer()
                                    /*@START_MENU_TOKEN@*/Text("Connexion")
                                        .font(.title2)
                                        .fontWeight(.bold)
                                        .foregroundColor(Color.white)
                                        .frame(height: 50.0)/*@END_MENU_TOKEN@*/
                                    Spacer()
                                }
                            }
                            .background(/*@START_MENU_TOKEN@*//*@PLACEHOLDER=View@*/Color("BackgroundColor")/*@END_MENU_TOKEN@*/)
                            .cornerRadius(/*@START_MENU_TOKEN@*/25.0/*@END_MENU_TOKEN@*/)
                        }
                        
                        
                    }
                    
                    .frame(width: 400.0, height: 400.0)
                    
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    
                    
                }
            )
    }
    
    struct ContentView_Previews: PreviewProvider {
        @State static var userTest = User(userId: -1,userMail: "", userPassword: "", communityId: -1, communityTitle: "")
        static var previews: some View {
            ConnexionView(user: $userTest)
        }
    }
}
