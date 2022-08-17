
//
//  Admin.swift
//  ExprimonsStats
//
//  Created by Theo Torres da costa on 23/05/2022.
//

import Foundation
import Alamofire

struct loggedAdmin:Hashable,Codable{
    let idAdmin:Int?
    let idCommunity:Int?
    let token:String?
}

func logAdmin (email:String,password:String){
    let params: Parameters = [
            "email": email,
            "password": password,
            
        ]
    AF.request("https://www.titan-photography.com/admin/loginSecure", method: .post, parameters: params, encoding: JSONEncoding.default, headers: nil).validate(statusCode: 200 ..< 299).responseData { response in
            switch response.result {
                case .success(let data):
                    do {
                        guard let jsonObject = try JSONSerialization.jsonObject(with: data) as? [String: Any] else {
                            print("Error: Cannot convert data to JSON object")
                            return
                        }
                        /*
                        guard let prettyJsonData = try? JSONSerialization.data(withJSONObject: jsonObject, options: .prettyPrinted) else {
                            print("Error: Cannot convert JSON object to Pretty JSON data")
                            return
                        }
                        
                        guard let prettyPrintedJson = String(data: prettyJsonData, encoding: .utf8) else {
                            print("Error: Could print JSON in String")
                            return
                        }
                         */
                        UserDefaults.standard.set(jsonObject["idAdmin"] as! Int, forKey: "idAdmin")
                        UserDefaults.standard.set(jsonObject["idCommunity"] as! Int, forKey: "idCommunity")
                        UserDefaults.standard.set(jsonObject["token"] as! String, forKey: "token")
                        print("idAdmin in function: "+String(UserDefaults.standard.integer(forKey: "idAdmin")))
                        
                    } catch {
                        print("Error: Trying to convert JSON data to string")
                        return
                    }
                case .failure(let error):
                    print(error)
            }
        }
}












