import java.sql.{Connection,DriverManager}
//1st version 
try{
	Class.forName("com.mysql.jdbc.Driver")
	var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "")
	val statement = connection.createStatement
	val rs = statement.executeQuery("SELECT * FROM personal")
	println("-------------------------------------------")
	
	def startingpoint(){
	println("-------------------------------------------")
	println("Select an option:")
	println("1 to create an account")
	println("2 to deposit money")
	println("3 to withdraw money")
	println("4 to exit the program")

	var optionSelect = (scala.io.StdIn.readInt())
	optionSelect match {
	//Match statement							
		case 1 => {
			println("Enter in Name and Address")
			createAccount(scala.io.StdIn.readLine(),scala.io.StdIn.readLine())
			println("Account created")
			startingpoint()
	}
		case 2 =>{
			depositMoney()
		}
		case 3 =>{
			withdrawMoney()
		}
	    case 4 =>{
	    	exit()
	    }
		case _ => println {
			println("You have entered an incorrect option")
			startingpoint() 
		}
	}
	}
	
	
	
	
	//Function to create an account which will then insert into the SQL table
	def createAccount(Name:String,Address:String)={
		statement.executeUpdate("INSERT into personal values(null,'"+Name+"','"+Address+"')")
	}

	def depositMoney()={
		println("Please enter your accountNumber and the amount of money you wish to deposit")
		var acno = scala.io.StdIn.readInt
		val name1 = statement.executeQuery("SELECT * FROM personal WHERE ACNO = "+acno)
		name1.next
		var name2 = name1.getString("Name")
		println(name2) 
		
		val address1 = statement.executeQuery("SELECT * FROM personal where ACNO = "+acno)
		address1.next
		var address2 = address1.getString("Address")
		println(address2)
		
		var amount = scala.io.StdIn.readInt
		println("Save the record Yes(1) or No(2)")
		var save = scala.io.StdIn.readInt
		match {
			case 1 =>{
				statement.executeUpdate("INSERT into deposit values("+acno+",+"+amount+",now())")
				println("Deposit has been processed")
				startingpoint()
			}
			case 2 =>{
				startingpoint()
			} 
		}
	}

	def withdrawMoney()={
		println("Please enter the accountNumber and money that you would like to withdraw")
		//Insert the account number
		var acno = scala.io.StdIn.readInt
		//Displays name
		val name1 = statement.executeQuery("Select * FROM personal WHERE ACNO ="+acno)
		if(name1.next){
			var name2 = name1.getString("Name")
			println("Name: " + name2)
		}
		//Displays address
		val address1 = statement.executeQuery("SELECT * FROM personal where ACNO = "+acno)
		if(address1.next){
			var address2 = address1.getString("Address")
			println("Address: " + address2)
		}
		//Insert amount
		var amount = scala.io.StdIn.readInt
		println("Save the record Yes(1) or No(2)")
		var save = scala.io.StdIn.readInt
		//Checks whether balance is more than the amount that the user is asking for
		//If more then the user will prompted and program will exit
		save match {
			case 1 =>{
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
			case 2 =>{
				sys.exit
			} 
			
	}
}
def exit()={
	println("Good Bye")
	sys.exit
}
startingpoint()
//End of try block
}
	
	catch{
		case e: Exception => println(e)
	}

	//Connection.close
