package weaponshop;

public class Pretty {
        public static String UI(int fill, String str, int pos, String border, boolean newline) // Function to print formated text to UI
        {
            String output = "";
            String spaceStr = "";
            int strLength = str.length();
            fill = border.length() > 0 ? fill - (border.length() * 2) : fill;
            int spaceNum = pos == 3 ? (fill - strLength) / 2 : fill - strLength;
    
            for (int i = 0; i < spaceNum; i++)
            {
                spaceStr += " ";
            }
            output += border;
            switch (pos)
            {
                case 1:
                    output += str + spaceStr;
                    break;
    
                case 2:
                    output += spaceStr + str;
                    break;
    
                default:
                    output += spaceStr + str + spaceStr;
                    break;
            }
            output += border;
            output += output.length() < fill ? " " : "";
    
            if(newline == true)
            {
                output += "\n";
            }
    
            return output;
        }



        
        public static String UI(int fill, String str)
        {
            int pos = 3;
            String border = ""; 
            boolean newline = true;
            return UI(fill, str, pos, border, newline);
        }
        public static String UI(int fill) 
        {
            String str = " ";
            int pos = 3;
            String border = ""; 
            boolean newline = true;
            return UI(fill, str, pos, border, newline);
        }
}
    
