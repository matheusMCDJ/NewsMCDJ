package br.iesb.newsmcdj.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.interactor.repository.model.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btSignUp.setOnClickListener { signUp() }
    }

    private fun signUp() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(this, "E-mail obrigatório!", Toast.LENGTH_LONG).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Senha obrigatória!", Toast.LENGTH_LONG).show()
            return
        } else {
            if (password.length < 6) {
                Toast.makeText(
                    this,
                    "A senha precisa ter 6 caracteres no mínimo.",
                    Toast.LENGTH_LONG
                ).show()
                return
            }
        }

        val operation = mAuth.createUserWithEmailAndPassword(email, password)
        operation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val profileRepository = ProfileRepository()
                profileRepository.updateProfile(email)
                Toast.makeText(this, "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                val error = task.exception?.localizedMessage ?: "Não foi possível entrar no aplicativo no momento"
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
