package br.iesb.newsmcdj.interactor.repository.model

import android.widget.Toast
import br.iesb.newsmcdj.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private var profile: Profile? = null

    fun updateProfile(email: String, name : String?=null, phone: String?=null): String {
        var saveMessage: String

        profile = Profile(
            email = email,
            name = name,
            phone = phone
        )

        val uid = mAuth.currentUser?.uid

        if (uid != null) {
            val userProfile = database.getReference("profile/$uid")
            userProfile.setValue(profile)
            saveMessage = "Dados de perfil salvos!"
            //Toast.makeText(this@ProfileActivity, "Dados de perfil salvos!", Toast.LENGTH_LONG).show()
        } else {
            saveMessage = "Não foi possível recuperar a chave do usuário!"
            //Toast.makeText(this@ProfileActivity, "Não foi possível recuperar a chave do usuário!", Toast.LENGTH_LONG).show()
        }
        return saveMessage
    }
}