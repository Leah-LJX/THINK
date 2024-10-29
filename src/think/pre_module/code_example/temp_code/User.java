import com.example.PDUserProperty;

public class User {
   public PDUserProperty property;

   public User(PDUserProperty property) {
       this.property = property;
   }

   public void printPropertyName() {
       String propertyName = property.getName();
       System.out.println("Property name: " + propertyName);
   }
}