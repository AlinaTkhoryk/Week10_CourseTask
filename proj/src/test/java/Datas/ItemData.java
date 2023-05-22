package Datas;

public class ItemData {

    public String name;
    public String price;
    public String availability;
    public String code;
    public boolean IsSame(ItemData data){
        return name == data.name
                && price == data.price
                && availability == data.availability
                && code == data.code;
    }
}
