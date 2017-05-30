import java.util.Scanner; // Needed for Scanner class
import java.util.Random;

public class TicTacToeMariaVargas
{
    public static void main (String [] args)
    {
        splashScreen();
        switchPlayer();
    }


    public static void splashScreen()//displays game and developer's information
    {
        String userInput;
        char proceed = 'y';

        Scanner keyboard = new Scanner(System.in);

        System.out.println("***********************");
        System.out.println("***********************");
        System.out.println("***********************");
        System.out.println("******           ******");
        System.out.println("******TIC TAC TOE******");
        System.out.println("******  ByMaria  ******");
        System.out.println("******  Vargas   ******");
        System.out.println("******           ******");
        System.out.println("***********************");
        System.out.println("***********************");
        System.out.println("***********************");

        System.out.println();
        System.out.println("y/Y to continue, any other char to exit\n");
        userInput = keyboard.nextLine();

        int userAnswerLength = userInput.length();

        if(userAnswerLength == 0)//so if userInput is <ENTER> it closes and does not crash
            {
                System.out.println("Goodbye!");
                System.exit(0);
            }

        proceed = userInput.charAt(0);

        if(!(proceed == 'y' || proceed == 'Y'))// so that if anything besides y/Y closes
            {
		System.out.println("Goodbye!\n");
                System.exit(0);
            }

        for (int counter = 0; counter < 3; counter++)
            {
                System.out.println();
            }

    }//end splashScreen

    public static void switchPlayer()//switch from one player to another
    {
       //most of the methods will be called here
       char [] myArray  = {'0','1','2','3','4','5','6','7','8'}; //make a class
       String userInput;
       char quitOrContinue = 'y';
       int userChoice;
       boolean computerBlocked = false;
       boolean Win = false;
       boolean Tie = false;
       Scanner keyboard = new Scanner(System.in);

       int numberOfPlays = 0;
while(quitOrContinue == 'y')
{
        while (numberOfPlays < 9)
        {
            displayGrid(myArray);

            if (numberOfPlays % 2 == 0)
            {
                playerMakeMove(myArray);
                Win = checkWin(myArray);
                Tie = checkTie(numberOfPlays);
                if (Win == true)
				{
				    Tie = false;
                }
                if (Win == true || Tie == true)
				{
					numberOfPlays = 12;

               	}
            }

            else
            {
                System.out.println("\nIt's the computer's turn\n ");
                computerBlocked = computerBlock(myArray);
                if(computerBlocked == false)
                {
                    makeBestMove(myArray);
				}
                Win = checkWin(myArray);
                Tie = checkTie(numberOfPlays);
                if (Win == true)
				{
				  Tie = false;
                }

                if (Win == true || Tie == true)
				{
					numberOfPlays = 12;

               	}

            }

            numberOfPlays++;
        }//end while loop
        System.out.println ("Play again? Y/y\nAnything else will quit.\n");
		userInput = keyboard.nextLine();
		quitOrContinue = userInput.charAt(0);

		if(!(quitOrContinue == 'y' ||  quitOrContinue== 'Y'))
		{
			System.out.println("Goodbye!\n");
			System.exit(0);
		}
		else
		{
			resetGame(myArray);
			numberOfPlays = 0;
		}
}//end outer while loop

}//end switchPlayer

    public static void resetGame(char[] myArray)//resets the game when one concludes;this includes filling the array with values 0-8
    {
       myArray[0]  = '0';
       myArray[1]  = '1';
       myArray[2]  = '2';
       myArray[3]  = '3';
       myArray[4]  = '4';
       myArray[5]  = '5';
       myArray[6]  = '6';
       myArray[7]  = '7';
       myArray[8]  = '8';

    }//end resetGame

    public static void displayGrid(char [] myArray)//diplay the game after each player makes a move
    {

        System.out.print ("|" + myArray[0]);
        System.out.print ("|" + myArray[1]);
        System.out.println ("|" + myArray[2] + "|");
        System.out.print ("|" + myArray[3]);
        System.out.print ("|" + myArray[4]);
        System.out.println ("|" + myArray[5] + "|");
        System.out.print ("|" + myArray[6]);
        System.out.print ("|" + myArray[7]);
        System.out.print ("|" + myArray[8] + "|");
    }//end fisplayGrid

    public static void playerMakeMove(char[] myArray)//prompts player to make a move, invokes validatePlayersMove, checkPositionAvailability
    {
        Scanner keyboard = new Scanner(System.in);
        String userInput;
        int userChoice;

        System.out.println("\nIt's your turn.\n ");
        userInput = keyboard.nextLine();

        userInput = validatePlayersMove(userInput); //validate userInput
        userChoice = Integer.parseInt(userInput); //parse to integer
        userChoice = checkPositionAvailability(userChoice, myArray);//check for availablity
        myArray[userChoice] = 'H';//take spot
        checkWin(myArray);
    }//end playerMakeMove

    public static String validatePlayersMove(String userInput)//validates that user entry X is such as that 0<=X<=8
    {
        Scanner keyboard = new Scanner (System.in);

        while (!userInput.equals("0") && !userInput.equals("1")
               && !userInput.equals("2") && !userInput.equals("3")
               && !userInput.equals("4") && !userInput.equals("5")
               && !userInput.equals("6") && !userInput.equals("7")
               && !userInput.equals("8"))
        {
            System.out.println ("That is an invalid entry. Try again\n");
            userInput = keyboard.nextLine();
        }
        return userInput;

    }//end validatePlayersMove

    public static Integer checkPositionAvailability(int userChoice, char [] myArray)//check that the position selected by the user is available
    {
        String userAnswer;

        Scanner keyboard = new Scanner (System.in);

            while (userChoice != myArray[userChoice] - '0')
            {
                System.out.println ("That spot is taken. Try Again\n");
                userAnswer = keyboard.nextLine();
                userAnswer = validatePlayersMove(userAnswer);
                userChoice = Integer.parseInt(userAnswer);

            }
            return userChoice;
    }//end checkPostionAvailability

    public static boolean checkWin(char [] myArray)//check for a winning player
    {
        boolean Win = false;

        if (myArray [0] == myArray[1]
            && myArray [1] == myArray [2]
            && myArray [2] == 'C')//row 1
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [0] == myArray[1]
            && myArray [1] == myArray [2]
            && myArray [2] == 'H')//row 1
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [3] == myArray[4]
            && myArray [4] == myArray [5]
            && myArray [5] == 'C')//row 2
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [3] == myArray[4]
            && myArray [4] == myArray [5]
            && myArray [5] == 'H')//row 2
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [6] == myArray[7]
            && myArray [7] == myArray [8]
            && myArray [8] == 'C')//row 3
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [6] == myArray[7]
            && myArray [7] == myArray [8]
            && myArray [8] == 'H')//row 3
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [0] == myArray[3]
            && myArray [3] == myArray [6]
            && myArray [6] == 'C')//coulmn 1
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [0] == myArray[3]
            && myArray [3] == myArray [6]
            && myArray [6] == 'H')//coulmn 1
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [1] == myArray[4]
            && myArray [4] == myArray [7]
            && myArray [7] == 'C')//column 2
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

         if (myArray [1] == myArray[4]
            && myArray [4] == myArray [7]
            && myArray [7] == 'H')//column 2
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [2] == myArray[5]
            && myArray [5] == myArray [8]
            && myArray [8] == 'C')//column 3
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [2] == myArray[5]
            && myArray [5] == myArray [8]
            && myArray [8] == 'H')//column 3
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [0] == myArray[4]
            && myArray [4] == myArray [8]
            && myArray [8] == 'C')//diagonal 1
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [0] == myArray[4]
            && myArray [4] == myArray [8]
            && myArray [8] == 'H')//diagonal 1
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        if (myArray [2] == myArray[6]
            && myArray [6] == myArray [4]
            && myArray [4] == 'C')//diagonal 2
        {
            System.out.println ("Computer Wins.\n");
            Win = true;
        }

        if (myArray [2] == myArray[6]
            && myArray [6] == myArray [4]
            && myArray [4] == 'H')//diagonal 2
        {
            System.out.println ("You Win.\n");
            Win = true;
        }

        return Win;

    }//end checkWin

    public static boolean checkTie(int numberOfPlays)//check for a Tie
    {
        boolean Tie = false;
        System.out.println ("Number of plays = " + numberOfPlays + "\n");
        if (numberOfPlays == 8)
        {
            System.out.println("It's a tie.\n");
            Tie = true;
        }

        return Tie;

    }//end checkTie

    public static void makeBestMove(char [] myArray)//select best option
    {
		Random someRandomInt = new Random();
        boolean random = false;
        while (random == false)
        {
            int number = (someRandomInt.nextInt(8)) + 1;
            System.out.println(number);//to know what number is picked by the Computer
            if (myArray [number] != 'C' && myArray [number] != 'H')
            {
                myArray[number] = 'C';
                random = true;
            }
        }

    }//end makeBestMove

    public static boolean computerBlock(char [] myArray)//used to make the move, in other words populate the array
    {
        boolean computerBlocked = false;

        //row 1 (--0--1--2--)

        if (myArray[1] == myArray[2]
            && myArray[1] == 'H' && myArray[0] == '0')
        {
          myArray[0] = 'C';
          computerBlocked = true;
        }

        else if (myArray[0] == myArray[2]
            && myArray[0] == 'H' && myArray[1] == '1')
        {
          myArray[1] = 'C';
          computerBlocked = true;
        }

        else if (myArray[0] == myArray[1]
            && myArray[1] == 'H' && myArray[2] == '2')
        {
          myArray[2] = 'C';
          computerBlocked = true;
        }
        //end row 1

        //row 2  (--3--4--5--)
       else if (myArray[3] == myArray[4]
            && myArray[3] == 'H' && myArray[5] == '5')
        {
          myArray[5] = 'C';
          computerBlocked = true;
        }

       else if (myArray[3] == myArray[5]
            && myArray[3] == 'H' && myArray[4] == '4')
        {
          myArray[4] = 'C';
          computerBlocked = true;
        }

      else  if (myArray[4] == myArray[5]
            && myArray[4] == 'H' && myArray[3] == '3')
        {
          myArray[3] = 'C';
          computerBlocked = true;
        }
        //end row 1

        //row 3 (--6--7--8--)
      else  if (myArray[6] == myArray[7]
            && myArray[6] == 'H' && myArray[8] == '8')
        {
          myArray[8] = 'C';
          computerBlocked = true;
        }

     else if (myArray[6] == myArray[8]
            && myArray[6] == 'H' && myArray[7] == '7')
        {
          myArray[7] = 'C';
          computerBlocked = true;
        }

      else if (myArray[7] == myArray[8]
            && myArray[7] == 'H' && myArray[6] == '6')
        {
          myArray[6] = 'C';
          computerBlocked = true;
        }
        //end row 3

        //column 1 (--0--3--6--)
      else if (myArray[0] == myArray[3]
            && myArray[0] == 'H' && myArray[6] == '6')
        {
          myArray[6] = 'C';
          computerBlocked = true;
        }

      else if (myArray[0] == myArray[6]
            && myArray[6] == 'H' && myArray[3] == '3')
        {
          myArray[3] = 'C';
          computerBlocked = true;
        }

      else if (myArray[3] == myArray[6]
            && myArray[3] == 'H' && myArray[0] == '0')
        {
          myArray[0] = 'C';
          computerBlocked = true;
        }
        //column 1

        //column 2 (--1--4--7--)
       else if (myArray[1] == myArray[4]
            && myArray[1] == 'H' && myArray[7] == '7')
        {
          myArray[7] = 'C';
          computerBlocked = true;
        }

       else if (myArray[1] == myArray[7]
            && myArray[1] == 'H' && myArray[4] == '4')
        {
          myArray[4] = 'C';
          computerBlocked = true;
        }

       else if (myArray[4] == myArray[7]
            && myArray[4] == 'H' && myArray[1] == '1')
        {
          myArray[1] = 'C';
          computerBlocked = true;
        }
        //column 2

        //column 3 (--2--5--8--)
       else if (myArray[2] == myArray[5]
            && myArray[2] == 'H' && myArray[8] == '8')
        {
          myArray[8] = 'C';
          computerBlocked = true;
        }

       else if (myArray[2] == myArray[8]
            && myArray[2] == 'H' && myArray[5] == '5')
        {
          myArray[5] = 'C';
          computerBlocked = true;
        }

       else if (myArray[5] == myArray[8]
            && myArray[5] == 'H' && myArray[2] == '2')
        {
          myArray[2] = 'C';
          computerBlocked = true;
        }
        //column 3

        //diagonal 1 (--0--4--8--)
       else if (myArray[0] == myArray[4]
            && myArray[4] == 'H' && myArray[8] == '8')
        {
          myArray[8] = 'C';
          computerBlocked = true;
        }

       else if (myArray[0] == myArray[8]
            && myArray[8] == 'H' && myArray[4] == '4')
        {
          myArray[4] = 'C';
          computerBlocked = true;
        }

       else if (myArray[8] == myArray[4]
            && myArray[4] == 'H' && myArray[0] == '0')
        {
          myArray[0] = 'C';
          computerBlocked = true;
        }
        //diagonal 1

        //diagonal 2 (--2--4--6--)
       else if (myArray[2] == myArray[4]
            && myArray[4] == 'H' && myArray[6] == '6')
        {
          myArray[6] = 'C';
          computerBlocked = true;
        }

      	else if (myArray[2] == myArray[6]
            && myArray[6] == 'H' && myArray[4] == '4')
        {
          myArray[4] = 'C';
          computerBlocked = true;
        }

        else if (myArray[4] == myArray[6]
            && myArray[6] == 'H' && myArray[2] == '2')
        {
          myArray[2] = 'C';
          computerBlocked = true;
        }
        //diagonal 2

	return computerBlocked;
    }//end computerMakeMove

}


