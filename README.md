# Mobile_Dev

- Explain how you ensure user is the right one starting the app

The first activity launched by the app is a login activity, the password is 0000 and its hash is stored in the code to ensure that it cannot be recovered through decompiling the app.apk file.


- How do you securely save user's data on your phone ?

The user data are saved on a .txt file in the phone internal memory using this function :

val file = "Save.txt"
                val data = display.toString()
                val fileOutputStream: FileOutputStream
                try {
                    fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                    fileOutputStream.write(data.toByteArray())
                }catch (e: Exception){
                    e.printStackTrace()
                }
The Context.MODE_PRIVATE ensure that it can only be called by the application

- How did you hide the API url ?

The API url is hidden in a c++ file that is accessed as a library, making it harder to retrieve through decompilation of the apk
