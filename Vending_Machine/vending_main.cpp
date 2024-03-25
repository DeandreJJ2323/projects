//
//  main.cpp
//  Vending_machine
//
//  he
//

#include <iostream>
#include <string>
using namespace std;

// Items that will be contained in the vending machine
pair<string, double> p1("coke", .95);
pair<string, double> p2("Doritos", .75);
pair<string, double> p3("Snikers", .55);
pair<string, double> p4("Chex mix", .60);
pair<string, double> p5("Pepsi", .90);

//Global variables initialized to numerals for the storage of product quantity and details used for authorized users to update monetary features
int quarters = 20, dimes = 20, nickles = 20, cokemax = 30, dorritosmax = 30, snikermax = 30, chexmax = 30, pepsimax = 30, seccode = 556;
double dollarmax = 20.00, fivemax = 20.00, quartertray = 0, dimetray = 0, nickletray = 0, coinbag = 0, dollartray = 0, fivetray = 0, billbag = 0;

/* Menu function that is mainy used for maintenece purposes like restocking products and collecting bill and coins */
int menu()
{
    int start = 0, usercode = 0;
    cout << "Press start button to select an item, for other options enter security code " << endl;
    cin >> start;
    if(start == seccode)
    {
        cout << "Coin tray-\n Quarters: " << quarters << " \nDimes: " << dimes << " \nNickles: " << nickles << endl;
        cout << "----------------------------------------------------------------------------------" << endl;
        cout << "Bill tray:\n Dollar Bills: " << dollarmax << " \nFive Dollar Bills: " << fivemax << endl;
        tryagain:
        cout << "To refill items press 2, to remove coins press 3, to remove dollars press 4 " << endl;
        cin >> start;
        if(start == 2){
        return start;
        }else if(start == 3){
            return start;
        }else if(start == 4){
            return start;
        }
        else{
            cout << " Invalid answer, try again. " << endl;
            goto tryagain;
        }
    }
    else{
        start = 1;
    }
    return start;
}

/* The Item_selection function provides the interface that allows a person to choose a product while also calculating order total plus deduct the products quantity */

double item_selection()
{
    double totalsum = 0;
    bool done = false;
    int item;
    string final;

    while(done == false)
    {
        cout << "Shown is the Alpanumaric value given for each item, please select an item you'd like to purchase\n";
        
        cout << "---------------------------------------------------------------------------- " << endl;
        again:
        cout << " COKE: 1\n DORITOS: 2\n SNIKERS 3\n CHEX MIX 4\n PEPSI 5\n " << endl;
        cin >> item;
        switch(item)
        {
            case 1:
                if(cokemax == 0)
                {
                    cout << "Out of stock! " << endl;
                    goto again;
                }
                cout << "COKE selected\n" << endl;
                totalsum += p1.second;
                cokemax -= 1;
                break;
                
            case 2:
                if(dorritosmax == 0)
                {
                    cout << "Out of stock! " << endl;
                    goto again;
                }
                cout << "DORITOS selected\n" << endl;
                totalsum += p2.second;
                dorritosmax -= 1;
                break;
                
            case 3:
                if(snikermax == 0)
                {
                    cout << "Out of stock! " << endl;
                    goto again;
                }
                cout << "SNIKERS selected\n" << endl;
                totalsum += p3.second;
                snikermax -= 1;
                break;
                
            case 4:
                if(chexmax == 0)
                {
                    cout << "Out of stock! " << endl;
                    goto again;
                }
                cout << "CHEX MIX selected\n" << endl;
                totalsum += p4.second;
                chexmax -= 1;
                break;
                
            case 5:
                if(pepsimax == 0)
                {
                    cout << "Out of stock! " << endl;
                    goto again;
                }
                cout << "PEPSI selected\n" << endl;
                totalsum += p5.second;
                pepsimax -= 1;
                break;
                
            default:
                cout << item << " Is invalid, try again " << endl;
                goto again;
        }
        rerun:
        cout << "Would that complete your order ? Y for yes N for no" << endl;
        cin >> final;
        
        if(final == "Y" )
        {
            done = true;
        }
        else if(final == "N"){
            done = false;
            goto again;
        }
        else{
            cout << final << " Invalid answer try again" << endl;
            goto rerun;
        }
    }
    
    cout << "Total: " << totalsum << endl;
    return totalsum;
}

/* The collectcoins function collects the coins of the person selecting products in the vending machine */

double collectcoins()
{
    double cointotal = 0, coin;
    bool done = false;
    string choice;
    while(done == false)
    {
        again:
        cout << "enter coins (Quarters, Dimes, Nickles only) " << endl;
        cin >> coin;
        if(coin == .25){
            quarters += 1;
        cointotal +=.25;
        }else if(coin == .10){
            dimes += 1;
            cointotal += .10;
        }else if(coin == .05){
            nickles += 1;
            cointotal += .05;
        }else if(coin == 0){
            done = true;
            break;
        }else{
            cointotal += 0.00;
            cout << coin << " is a Invalid currency, please try again " << endl;
            goto again;
        }
            
        cout << " Finished? Y for yes N for no " << endl;
        cin >> choice;
        if(choice == "Y")
        {
            done = true;
        }
        else if(choice == "N")
        {
            goto again;
        }
        else{
            cout << " Invalid answer" <<endl;
        }
    }
    return cointotal;
}

/* The collectcoins function collects the bills of the person selecting products in the vending machine */

double collectbills()
{
    double dollartotal = 0, bill;
    bool done = false;
    string choice;
    
    while(done == false)
    {
        again:
        cout << "enter bills (Dollars & Five's only) " << endl;
        cin >> bill;
        if(bill == 1)
        {
            dollarmax += 1.00;
            dollartotal += 1.00;
        } else if(bill == 5)
        {
            fivemax += 5.00;
            dollartotal += 5.00;
        }else if(bill == 0)
        {
            done = true;
            break;
        }
        else
        {
            cout << bill << " Is invalid try again " << endl;
            goto again;
            
        }
        cout << " Finished? Y for yes N for no " << endl;
        cin >> choice;
        if(choice == "Y")
        {
            done = true;
        }
        else if(choice == "N")
        {
            goto again;
        }
        else{
            cout << " Invalid answer" <<endl;
        }
            
    }
    
    return dollartotal;
}

/* The payment function takes in the users total and calculates the change that will be dispensed back to the user */

double payment(double total)
{
    double change = 0, coins, dollars;
    coins = collectcoins();
    dollars = collectbills();
    total -= dollars + coins;
    if(total > 0)
    {
        cout << " Please enter " << total << " more" << endl;
        cout << " When done inserting coins and dollars press 0 " << endl;
        coins = collectcoins();
        dollars = collectbills();
        double newchange = (dollars + coins) - change;
        cout << newchange << " will be despensed to you " << endl;
        return newchange;
    }
   
    cout << "Your total change is: " << total << endl;
    return total;
}

/* The restockitems function is used to allow authorized users restock product quantity or add to product invetory */

void restockitems()
{
    int item;
    again:
    cout << " Select item you want restocked " << endl;
    cout << endl;
    cout << " COKE: 1\n DORITOS: 2\n SNIKERS 3\n CHEX MIX 4\n PEPSI 5\n " << endl;
    cin >> item;
    switch (item) {
        case 1:
            if(cokemax <= 5)
            {
                cokemax +=25;
            }
            cout << " There are: " << cokemax << " units left, restock when the qauntity becomes 5 or less " << endl;
            break;
        case 2:
            if(dorritosmax <= 5)
            {
                cokemax +=25;
            }
            cout << " There are: " << dorritosmax << " units left, restock when the qauntity becomes 5 or less " << endl;
            break;
        case 3:
            if(snikermax<= 5)
            {
                snikermax +=25;
            }
            cout << " There are: " << snikermax << " units left, restock when the qauntity becomes 5 or less " << endl;
            break;
        case 4:
            if( chexmax <= 5)
            {
                chexmax +=25;
            }
            cout << " There are: " << chexmax << " units left, restock when the qauntity becomes 5 or less " << endl;
            break;
        case 5:
            if(pepsimax <= 5)
            {
                pepsimax +=25;
            }
            cout << " There are: " << pepsimax << " units left, restock when the qauntity becomes 5 or less " << endl;
            break;
        default:
            cout << "Invalid answer, try again " << endl;
            goto again;
            break;
    }
}
double remove_refillcoins()
{
    while(quarters < 100 ){
    cout << " Total amount of quarters: " << quarters << " No need to restock "<< endl;
        break;
    }
    cout << endl;
    while(dimes < 100 ){
        cout << " Total amount of Dimes: " << dimes << " No need to restock "<< endl;
        break;
    }
    cout << endl;
    while(nickles < 100 ){
        cout << " Total amount of Nickels: " << nickles << " No need to restock "<< endl;
        break;
    }
    
    if(quarters >= 100)
    {
        quartertray = .25 * quarters;
        quarters = 20;
        cout << " Quarter sum: " << quartertray << endl;
    }
    if(dimes >= 100)
    {
        dimetray = .10 * dimes;
        dimes = 20;
        cout << "Dime sum: " << dimetray<< endl;
    }
    if(nickles >= 100)
    {
        nickletray = .05 * nickles;
        nickles = 20;
        cout << "Nickle sum: " << nickletray << endl;
    }
    
    coinbag = quartertray + dimetray + nickletray;
    return coinbag;
}

double remove_refilldollars()
{
    while(dollarmax < 100 ){
    cout << " Total amount of dollars: " << dollarmax << ", No need to restock. "<< endl;
        break;
    }
    cout << endl;
    
    while(dimes < 300.00 ){
        cout << " Total amount of five dollar bills: " << fivemax << ", No need to restock. "<< endl;
        break;
    }
    cout << endl;
    
    if(dollarmax >= 100.00)
    {
        dollartray = 1.00 * dollarmax;
        dollarmax = 20;
        cout << " Dollar sum: " << dollartray << endl;
    }
    if(fivemax >= 300.00)
    {
        fivetray = 5.00 * fivemax;
        fivemax = 20;
        cout << " Five bill sum: " << fivetray << endl;
    }
    billbag = fivetray + dollartray;
        return billbag;
}
/* Machinepower function is used to simply power on/off vending machine */
bool machinepower()
{
    bool on = false;
    cout << " Powering up machine " << endl;
    on = true;
    return on;
}

/* The following 6 functions are extras that can be used for analytical data containing the vending machine */

double comp_saleperhour(int totalsold, int totalhour)
{
    double soldperhour = totalsold / totalhour;
    
    return soldperhour;
}
double comp_saleperday(int totalsoldinday, double saleprice)
{
    double soldperday = totalsoldinday * saleprice;
    return soldperday;
}
double comp_totalsale(double avgprice, int units)
{
    double totalcost = avgprice * units;
    return totalcost;
}
double laborcost(double sellingprice)
{
    double laborcost = sellingprice * .25;
    cout << " Labor cost is: " << laborcost << endl;
    return laborcost;
}
double overhead(double sellingprice)
{
    double overhead = sellingprice * .05;
    cout << " overhead cost is: " << overhead << endl;
    return overhead;
}
double comp_profit()
{
    double avgprice = 0, revprofit = 0, profit = 0;
    int units = 0;
    cout << " Enter avg price for desired item " << endl;
    cin >>avgprice;
    cout << "Enter units " << endl;
    cin >> units;
    profit = comp_totalsale(avgprice, units);
    revprofit = (profit - avgprice) / profit;
    return revprofit;
}

/* Driver function */

int main(int argc, const char * argv[]) {
    double change,total = 0, cointotal = 0, dollartotal = 0;
    
    //change = payment(total);
    //cout << change << endl;
    //cout << quarters << endl;
    int option = 0;
    bool power = machinepower;
    while(machinepower)
    {
        option = menu();
        switch(option)
        {
            case 1:
                total = item_selection();
                change = payment(total);
                cout << change << endl;
                break;
            case 2:
                restockitems();
                break;
                
            case 3:
                remove_refillcoins();
                break;
                
            case 4:
              remove_refilldollars();
                break;
        }
    }
    return 0;
}

