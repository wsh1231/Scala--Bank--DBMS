import java.sql.{Connection,DriverManager}
//2nd version 
class databaseApplication{
	Class.forName("com.mysql.jdbc.Driver")
		var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "")
		val statement = connection.createStatement
		val rs = statement.executeQuery("SELECT * FROM personal")
		println("-------------------------------------------")

	def startingpoint()
	{
		try
		{
		println("-------------------------------------------")
		println("Please select an option(Use Number keys to select)")
		println("Create an account(1),Deposit Money(2),Withdraw Money(3),Show Balance(4),Exit(5)")

		var optionSelect = (scala.io.StdIn.readInt())
		optionSelect match {
			case 1 =>
			{
				println("Enter in name and address")
				createAccount(scala.io.StdIn.readLine(),scala.io.StdIn.readLine())
				startingpoint()	
			}
			case 2 => depositMoney()
			case 3 => withdrawMoney()
			case 4 => showBalance()
			case 5 => exit()
			case _ =>{
				println("Incorrect option entered")
				startingpoint()
			}

		    }
	    }
		catch{
			case e: Exception => println(e)
		}
	}

	def createAccount(Name:String,Address:String)={
		try{
			statement.executeUpdate("INSERT into personal values(null,'"+Name+"','"+Address+"')")
			val nameID = statement.executeQuery("SELECT max(acno) as AccountNo from personal ")
			nameID.next
			val findID = nameID.getInt("AccountNo")
			println("Your account number is: "+findID)
		}
		catch{
			case e: Exception => println(e)
			startingpoint()
		}
	}

		def depositMoney()={
			try{
			println("Enter account number and money to deposit")
			var acno = scala.io.StdIn.readInt
			val name1 = statement.executeQuery("SELECT * FROM personal WHERE ACNO = "+acno)
			name1.next
			var name2 = name1.getString("Name")
			println("Name: " + name2)
			var address2 = name1.getString("Address")
			println("Address: " + address2)
			var amount = scala.io.StdIn.readInt
			println("Save record Y(1), N(2)")
			var save = scala.io.StdIn.readInt
			save match {
				//Number keys are used to access the options
				case 1 =>{
					statement.executeUpdate("INSERT into deposit values("+acno+",+"+amount+",now())")
					println("Deposit has been processed")
					startingpoint()
				}
				case 2 => startingpoint()
				case _ => {
					println("Invalid Input")
					startingpoint()
				}

			}
			} catch{
				case e: Exception => {
					println(e)
					println("Account Number does not exist")
					startingpoint()
				}
			}
		
		}

		def withdrawMoney()={
			try{
			println("Enter account number and money to withdraw")
			var acno = scala.io.StdIn.readInt
			val name1 = statement.executeQuery("SELECT * FROM personal where ACNO = "+acno)
			name1.next 
			var name2 = name1.getString("Name")
			println("Name: " + name2)
			var address2 = name1.getString("Address")
			println("Address: " +address2)
			var amount = scala.io.StdIn.readInt
			println("Save record Y(1), N(2)")
			var save = scala.io.StdIn.readInt
			save match{
				case 1=>{
				val withdrawAmount = statement.executeQuery("SELECT SUM(Amount) FROM withdraw WHERE acno = "+acno)
				withdrawAmount.next
				val withdrawAmount1 = withdrawAmount.getInt("SUM(Amount)")
				val depositAmount = statement.executeQuery("SELECT SUM(Amount) FROM deposit WHERE acno = "+acno)
				depositAmount.next
				val depositAmount1 = depositAmount.getInt("SUM(Amount)")
				val balance = depositAmount1 - withdrawAmount1
				if(balance>= amount){
					statement.executeUpdate("INSERT into withdraw values("+acno+",+"+amount+",now())")
					println("Transaction processed")
					startingpoint()
				}else{
					println("You do not have enough available funds")
					startingpoint()
				}
			}
				case 2 => exit()
				case _ => {
					println("Invalid Input")
					startingpoint()
				}

		}
	} catch{
	case e: Exception =>{
		println(e)
		println("Account number does not exist")
		startingpoint()
	} 
	}
			
	}

	def exit()={
			println("Good Bye")
			sys.exit
		}

	def showBalance()={
		println("Enter in account number")
		var acno = scala.io.StdIn.readInt
			val name1 = statement.executeQuery("SELECT * FROM personal where ACNO = "+acno)
			name1.next 
			var name2 = name1.getString("Name")
			println("Name: " + name2)
			var address2 = name1.getString("Address")
			println("Address: " +address2)
			val withdrawAmount = statement.executeQuery("SELECT SUM(Amount) FROM withdraw WHERE acno = "+acno)
			withdrawAmount.next
			val withdrawAmount1 = withdrawAmount.getInt("SUM(Amount)")
			val depositAmount = statement.executeQuery("SELECT SUM(Amount) FROM deposit WHERE acno = "+acno)
			depositAmount.next
			val depositAmount1 = depositAmount.getInt("SUM(Amount)")
			val balance = depositAmount1 - withdrawAmount1
			println("Balance: " + balance)
			startingpoint()
	}
}


//Create object
var ref = new databaseApplication()
ref.startingpoint()
