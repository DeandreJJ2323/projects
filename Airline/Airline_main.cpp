//
//  main.cpp
//  Airline_management
//
//  
//

#include <iostream>
#include "Airline_classes.hpp"


int main()
{
    int menuchoice = 0;
    bool ans, dest;
    string first, last, add, idnumber,phonenumber;
    int dd;
    
   cout << "Welcome to DeAndre Johnsons airport kiosk, here you can book your destined flight without the hassle of dealing with representatives. ";
    
    cout << " Here at DJ AIR, We are a private airport based out of Houston texas and been established since october of 1996 " << endl;
    again2:
    cout << "To get started we just need a couple of things from you " << endl;
    
    cout << "Please enter your First name " << endl;
    getline(cin, first);
    cout << "Please enter your Last name " << endl;
    getline(cin, last);
    cout << "Please enter your Phone Number " << endl;
    getline(cin, phonenumber);
    cout << "Please enter your Address " << endl;
    getline(cin, add);
    cout << "Please enter your ID or Passport number " << endl;
    getline(cin, idnumber);
    cout << "Please enter your departdate " << endl;
    cin >> dd;
     
    Booking pass(first, last, phonenumber, add, idnumber, dd);
    Booking* obj;
    obj = &pass;
    again:
    cout << "Now that we have all your information, would you like to (1) book a flight or (2)change a flight ? " << endl;
    cin >> menuchoice;
    if(menuchoice == 1)
    {
    dest = obj->ldest();
    if(dest == true)
    {
        obj->seats();
        obj->registration();
        ans = obj->Kioskscreen();
        if(ans == true)
        {
            cout << "Purchased confirmed, thank you. please proceed to your gate " << endl;
        }
    }
    }else if(menuchoice == 2)
    {
        cout << "Changing flight " << endl;
        obj->changeflight();
    }
    else{
        cout << "Invalid Choice, try again " << endl;
        goto again;
    }
    
    return 0;
}

