package br.iesb.newsmcdj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPasswordActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btForgotPassword.setOnClickListener{ forgotPass() }
    }

    private fun forgotPass() {

        val email = etEmail.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "E-mail obrigatório!", Toast.LENGTH_LONG).show()
            return
        }

        val operation = mAuth.sendPasswordResetEmail(email)

        operation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Email de redefinição enviado!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                val error = task.exception?.localizedMessage ?: "Não foi possível entrar no aplicativo no momento"
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
