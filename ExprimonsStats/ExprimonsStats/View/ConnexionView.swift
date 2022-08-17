//
//  ContentView.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 01/04/2022.
//

import SwiftUI


struct ConnexionView: View {
    
    
    //variable de test
    @State private var emailTest:String = ""
    @State private var passwordTest:String = ""
    init(){
        /*
        print("idAdmin: "+String(UserDefaults.standard.integer(forKey: "idAdmin")))
        print("idCommunity: "+String(UserDefaults.standard.integer(forKey: "idCommunity")))
        print("token: "+(UserDefaults.standard.string(forKey: "token") ?? "null"))
         */
        /*
        if let bundleID =  Bundle.main.bundleIdentifier {
            UserDefaults.standard.removePersistentDomain(forName: bundleID)
        }
         */
        
        
        
    }
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
                                
                                TextField(/*@START_MENU_TOKEN@*/"Email"/*@END_MENU_TOKEN@*/, text: $emailTest)
                                    .autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                                    .disableAutocorrection(true)
                                
                                
                                
                                SecureField("Mot de passe", text:$passwordTest)
                                    .foregroundColor(/*@START_MENU_TOKEN@*/.blue/*@END_MENU_TOKEN@*/)
                            }
                            Button(action:  {
                                Task{
                                    logAdmin(email: emailTest, password: passwordTest)
                                    DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) { // Change `2.0` to the desired number of seconds.
                                        // Code you want to be delayed
                                        if(UserDefaults.standard.integer(forKey: "idAdmin") != 0){
                                            print("connected")
                                            
                                        }
                                        else{
                                            print("notConnected")
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
                            .onAppear{
                                //admin.fetch()
                                
                            }
                        }
                        
                        
                    }
                    .frame(width: 400.0, height: 400.0)
                    .cornerRadius(/*@START_MENU_TOKEN@*/50.0/*@END_MENU_TOKEN@*/)
                    .shadow(radius: /*@START_MENU_TOKEN@*/10/*@END_MENU_TOKEN@*/)
                    
                    
                }
            )
    }
    
    struct ContentView_Previews: PreviewProvider {
        
        static var previews: some View {
            ConnexionView()
        }
    }
}
