import java.util.Scanner;

public class GroceryShopping
{
    // Extra project task - Item Search Functionality
    private static void searchItem(String[] itemsA, String item){
        System.out.println("You entered: " + item);
        for(int i = 0; i < itemsA.length; i++){
            if (itemsA[i].equals(item))
            {
                System.out.println("The index of the item is " + i);
            }

        }
    }
    // Calculate the average price of the items
    private static void calculateAveragePrice(Float[] price){
        float prices = 0f;
        float average = 0f;
        for (float v : price) {
            prices += v;
        }
        average = prices/price.length;
        System.out.println("The average price is:  " + average);
    }

    // Project task Filter Items Below a Certain Price
    private static void filterItemsBelowPrice(String[] items, Float[] price){
        System.out.println("The price threshold is $3.00.");
        System.out.println("All items below $3.00");
        for (int i = 0; i < items.length; i++){
            if (price[i] < 3.00) {
                System.out.println(items[i] + "  " + price[i]);
            }
        }
    }

    // View inventory list
    private static void viewList(String[] item, Float[] price, Integer[] stock){
        System.out.println("Item\t\tPrice\tIn Stock" );

        for(int i = 0; i < item.length; i++)
        {
            if(item[i].length() < 8){
                System.out.println(item[i] + "\t\t" + price[i] + "\t\t" + stock[i]);
            }else
            {
                System.out.println(item[i] + "\t" + price[i] + "\t\t" + stock[i]);
            }
        }
    }

    public static void main(String[] args) throws ItemNotFoundException
    {
        String[] item = new String[10];
        Float[] price = new Float[10];
        Integer[] stock = new Integer[10];

        item[0] = "lettuce"; price[0] = 2.34f ;stock[0] = 4;
        item[1] = "potatoes"; price[1] = 3.68f ;stock[1] = 10;
        item[2] = "noodles"; price[2] = 1.45f ;stock[2] = 4;
        item[3] = "chicken"; price[3] = 5.99f ;stock[3] = 0;
        item[4] = "apples"; price[4] = 3.56f ;stock[4] = 24;
        item[5] = "oranges"; price[5] = 6.76f ;stock[5] = 12;
        item[6] = "flour"; price[6] = 3.45f ;stock[6] = 4;
        item[7] = "salt"; price[7] = .98f ;stock[7] = 5;
        item[8] = "pepper"; price[8] = 1.15f ;stock[8] = 10;
        item[9] = "eggs"; price[9] = 2.56f ;stock[9] = 36;

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            float totalBill = 0.00f;

            System.out.println(" *** Welcome to the Grocery Shopping App ***");
            System.out.println(" =========== Current Inventory =============\n");
            // show the iventory

            viewList(item, price, stock);

            while(true)
            {
                try {

                    System.out.print("Enter the name of the item (or type 'finish' to end shopping):");
                    String inputItem = scanner.nextLine(); // Get item from list
                    // Check if the user wants to finish shopping
                    if (inputItem.equalsIgnoreCase("finish")) {
                        System.out.println("Your total bill is: $" + totalBill);
                        // Apply discount if over $100
                        if(totalBill > 100) {
                            final float pct = .10f;
                            float discounted = 0f;
                            discounted = totalBill * pct;
                            float finalPrice = totalBill - (totalBill* pct);
                            System.out.println("Because your purchase is over $100 you receive a 10% discount.");
                            System.out.println("Your discount is $" + discounted);
                            System.out.println("Your final price is $" + finalPrice);
                        }
                        System.out.println("Thank you for shopping with us!");
                        break; // Exit the inner loop
                    }
                    // Find the index of the item in the array
                    int itemIndex = -1;
                    for (int i = 0; i < item.length; i++) {
                        if (item[i].equalsIgnoreCase(inputItem)) {
                            itemIndex = i;
                            break;
                        }
                    }
                    // If the item is not found, throw the custom exception
                    if (itemIndex == -1) {
                        throw new ItemNotFoundException("Item '" + inputItem + "' not found. Please try again.");
                    }
                    // Ask for the quantity of the item
                    System.out.print("Enter the quantity of " + item[itemIndex] + ": ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Check stock of item
                    if(stock[itemIndex] <= 0) {
                        System.out.println("Item is out of stock");

                    } else if (quantity > stock[itemIndex])
                    {
                        System.out.println("Insufficient quantity per request.");
                        System.out.println("Adjust quantity. Only " + stock[itemIndex] + " in stock.");
                        System.out.print("Enter the quantity of " + item[itemIndex] + ": ");
                        quantity = scanner.nextInt();
                        // Reduce the stock amount of the item
                        stock[itemIndex] = stock[itemIndex] - quantity;
                        // Calculate the cost for the item and add it to the total bill
                        float itemCost = price[itemIndex] * quantity;
                        totalBill += itemCost;
                        System.out.println("Added " + quantity + " x " + item[itemIndex] + " to the bill. Current total: $" + totalBill);
                    } else
                    {
                        float itemCost = price[itemIndex] * quantity;
                        totalBill += itemCost;
                        stock[itemIndex] = stock[itemIndex] - quantity;
                        System.out.println("Added " + quantity + " x " + item[itemIndex] + " to the bill. Current total: $" + totalBill);
                    }
                    System.out.print("Do you need to see the list again? (Y/N) ");
                    String yesNo = scanner.nextLine();
                    if(yesNo.equalsIgnoreCase("Y")){
                        viewList(item, price, stock);
                    }

                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage()); // Print the exception message
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            // Exit the program
            System.out.print("Enter 'exit' to leave the program ");
            String userInput  = scanner.nextLine();
            if(userInput.equalsIgnoreCase("exit"))
            {
                System.out.println("Thanks for using the shopping cart, Goodbye");
                // break;
            }
            break;
        }
    }
}




