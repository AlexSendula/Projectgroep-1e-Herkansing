package Shop;

public class CurrencyAdapterImpl implements CurrencyAdapter {

    @Override
    public double getTotalPrice(ShoppingCart shoppingCart){
        return convertEurToDol(shoppingCart.getTotalPrice());
    }
    private double convertEurToDol(double eur){
        return ((double) Math.round((eur*1.1993499)*100))/100;
    }
}
