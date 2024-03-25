//
//  Airline_classes.hpp
//  Airline_management
//
//  
//

#ifndef Airline_classes_hpp
#define Airline_classes_hpp
#endif /* Airline_classes_hpp */

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <ostream>

using namespace std;


class passenger
{
private:
    string first_name, last_name, phone, passport,address, o, d;
        int firstseats = 20, firstseatsprice = 85, ecoseatsprice = 40, ecoseats = 35, datedep, payment, numoftickets, option;
        vector<string> local_dest = {"MIAMI","LOS ANGELES","DALLAS","NEW YORK", "HOUSTON"};
        //vector<string> international_dest = {"LONDON","SPAIN","SYDNEY","CHINA"}; //FOR INTERNATIONAL DESTINAATION FUNCTION
        vector<string> origin = {"HOUSTON","MIAMI","LOS ANGELES","DALLAS","NEW YORK"};
    int local_payment(string o, string d);
    
    public:
    passenger(string f_name, string l_name, string phonenum, string addr, string passportnum = "N/A", int departdate = 000000);
    
    bool ldest();
    void seats();
    void registration();
    bool confirmation();
    
};

class Booking: public passenger
{
protected:
    int n , m;
public:
    Booking(string f_name, string l_name, string phonenum, string add, string pp, int dated): passenger(f_name, l_name, phonenum, add, pp, dated)
    {}
    
    bool Kioskscreen();
    
    void changeflight();
};
