//
//  Airline_classes.cpp
//  Airline_management
//
//  
//

#include "Airline_classes.hpp"
#include <iostream>


using namespace std;

int passenger::local_payment(string o, string d)
{
    int payment = 0;
    if((o == "HOUSTON" && d == "MIAMI") || (o == "MIAMI" && d == "HOUSTON"))
    {
        payment = 267;
    }else if((o == "HOUSTON" && d == "LOS ANGELES") || (o == "LOS ANGELES" && d == "HOUSTON"))
    {
        payment = 285;
    }
    else if((o == "HOUSTON" && d == "DALLAS") || (o == "DALLAS" && d == "HOUSTON")){
        payment = 150;
    }else if((o == "HOUSTON" && d == "NEW YORK") || (o == "NEW YORK" && d == "HOUSTON"))
    {
        payment = 270;
    }
    else if((o == "HOUSTON" && d == "NEW YORK") || (o == "NEW YORK" && d == "HOUSTON")){
        
    }
    return payment;
}

passenger:: passenger(string f_name, string l_name, string phonenum, string addr, string passportnum , int departdate)
{
    first_name = f_name;
    last_name = l_name;
    passport = passportnum;
    address = addr;
    phone = phonenum;
    datedep = departdate;
}

bool passenger:: ldest()
{
    again:
    cout << "The cities shown are our local destination cities " << endl;
    for(int i = 0; i < local_dest.size(); i++)
    {
        cout << local_dest[i] << endl;
    }
    //cout << "Enter origin: " << endl;
    //getline(cin, o);
    
    cout << "Enter destination " << endl;
    getline(cin, d);
    
    if( o == d )
    {
        cout << "Invalid input try again " << endl;
        goto again;
    }

    int n = 0, m = 0;
    for(int i = 0; i < origin.size(); i++)
    {
        if("HOUSTON" == local_dest[i])
        {
            n++;
        }
    }
    for(int j = 0; j < local_dest.size(); j++)
    {
        if(d == local_dest[j])
        {
            m++;
        }
    }
    if(m == 1 && n == 1)
    {
        payment = local_payment("HOUSTON", d);
        cout<<"Price: "<<payment<<" Per/seat"<<endl;
        return true;
    }
    else{
        return false;
    }
         
}

void passenger:: seats()
    {
        if(firstseats > 0 && ecoseats > 0)
        {
        again:
        cout << "For seating options, please choose one of the two options presented to you ! ";
        cout << "/n 1. For first class, 2. For economy" << endl;;
        cin >>option;
        if (option == 1)
        {
            cout << "First class " << endl;
            cout << "Please enter ticket quantity " << endl;
            cin >> numoftickets;
            if(numoftickets > firstseats){
                cout << "Invalid answer please try once more " << endl;
                goto again;
            }
            else if(firstseats <= 0){
                cout << "First Class seats are not available at the moment sorry " << endl;
            }else{
                cout << "There are " << firstseats << " seats available, after your ticket is comfirmed there will be ";
                firstseats -= numoftickets;
                payment = (firstseatsprice * numoftickets) + (payment * numoftickets);
                cout << firstseats << " available." << endl;
                cout << numoftickets <<" seats are booked." << endl;
            }
        } else if(option == 2){
            cout << "Economy class " << endl;
            cout << "Please enter ticket quantity " << endl;
            cin >> numoftickets;
            if(numoftickets > ecoseats){
                cout << "Invalid answer please try once more " << endl;
                goto again;
            }
            else if(ecoseats <= 0){
                cout << "Enonomy Class seats are not available at the moment sorry " << endl;
            }else{
                cout << "There are " << firstseats << " seats available, after your ticket is comfirmed there will be ";
                firstseats -= numoftickets;
                payment = (ecoseatsprice * numoftickets) + (payment * numoftickets);                cout << firstseats << " available." << endl;
                cout << numoftickets <<" seats are booked." << endl;
            }
        }
        else if(option != 1 || option != 2){
            cout << " Invalid input please try again." << endl;
            goto again;
        }
    }else{
        cout << "Sorry all seats are booked, we apologize for the inconveinence " << endl;
    }
        
    }
    
    void passenger:: registration()
    {
        int tkt;
        (time(NULL));
        tkt = rand()%1000;
        if(option == 1){
        ofstream outfile;
        outfile.open("flight.txt");
        outfile<<"|TICKET INFORMATION:"<<endl<<endl
        <<"Name: "<<first_name<<" "<<last_name<<endl
        <<"Phone: "<<phone<<endl
        <<"Address: "<<address<<endl
        <<"Passport Number/ ID Number: "<<passport<<endl
            <<"Origin: "<<o
            <<endl<<"Destination: "<<d
        <<endl<<"Number of Seats: "<<numoftickets
        <<endl<<"Class: First Class"
        <<endl<<"Gate: "<<datedep + rand()%10
        <<endl<<"Time: 5.30 PM"
        <<endl<<"Ticket: HF"<<tkt
        <<endl<<"recipt: "<<payment<<"/-"<<endl;
        outfile.close();
        } else {
            ofstream outfile;
            outfile.open("flight.txt");
            outfile<<"|TICKET INFORMATION:"<<endl<<endl
            <<"Name: "<<first_name<<" "<<last_name<<endl
            <<"Phone: "<<phone<<endl
            <<"Address: "<<address<<endl
            <<"Passport Number/ ID Number: "<<passport<<endl
            <<"Origin: "<< o
            <<endl<<"Destination: "<<d
            <<endl<<"Number of Seats: "<<numoftickets
            <<endl<<"Class: Economy Class"
            <<endl<<"Gate: "<<datedep + rand()%10
            <<endl<<"Time: 5.30 PM"
            <<endl<<"Ticket: HF"<<tkt
            <<endl<<"recipt: "<<payment<<"/-"<<endl;
            outfile.close();
        }
    }
    bool passenger:: confirmation()
    {
        w2:
        cout << " Do you wish to confirm your booking " << endl;
        string choice;
        cin >> choice;
        if(choice == "YES" || choice == "yes")
        {
            cout << "******** Booking confirmed *********  " << endl;
            ofstream output;
            output.open("flight.txt");
            cout << endl << "******** Booking confirmed *********  " << endl;
            return true;
            
        }else if(choice == "N" || choice == "n")
        {
            cout << "Booking canceled " << endl;
            return false;
        }else{
            cout << "error, try again " << endl;
            goto w2;
        }
    }
    

bool Booking:: Kioskscreen()
    {
        bool choice;
        string output;
        ifstream infile;
        infile.open("flight.txt");
        if(infile.fail())
        {
            cerr << "Error opening file " << endl;
            exit(1);
        }
        while(!infile.eof())
        {
            getline(infile, output);
            cout << output << endl;
        }
        choice = confirmation();
        return choice;
    }

void Booking:: changeflight()
{
    //bool choice;
    cout << "You are now about to change your flight, do you still wish to proceed ? " << endl;
    //cin ans
        this->ldest();
        this->seats();
        this->registration();
        this->Kioskscreen();
        cout << "Your changes are saved " << endl;
       
    
}





