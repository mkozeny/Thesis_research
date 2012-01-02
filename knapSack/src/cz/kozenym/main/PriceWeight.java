

package cz.kozenym.main;


public class PriceWeight {
    double price;
    double weight;
    
    
    public PriceWeight() {
        price = 0;
        weight = 0;
    }
    

    public double getWeight()
    {
        return weight;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public void addPrice(double c )
    {
        price += c;
    }
    public void addWeight(double v)
    {
        weight += v;
    }
    public void reset(){
        price = 0;
        weight = 0;
    }
    
}
