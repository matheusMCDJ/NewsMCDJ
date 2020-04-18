package br.iesb.newsmcdj.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.iesb.newsmcdj.R
import br.iesb.newsmcdj.model.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private var profile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        configure()
    }

    private fun configure() {
        val email = mAuth.currentUser?.email
        etEmail.setText(email)

        btSave.setOnClickListener { save() }

        val profiles = database.getReference("profile")

        val query = profiles.orderByChild("email").equalTo(email)

        query.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "Consulta cancelada!", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
//                profile = snapshot.children.first().getValue(Profile::class.java)
//                if (profile != null) {
//                    etName.setText(profile?.name)
//                    etPhone.setText(profile?.phone)
//                }
            }
        })
    }

    private fun save() {
        profile = Profile(
            email = etEmail.text.toString(),
            name = etName.text.toString(),
            phone = etPhone.text.toString()
        )

        val uid = mAuth.currentUser?.uid

        if (uid != null) {
            val userProfile = database.getReference("profile/$uid")
            userProfile.setValue(profile)
            Toast.makeText(this@ProfileActivity, "Dados de perfil salvos!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this@ProfileActivity, "Não foi possível recuperar a chave do usuário!", Toast.LENGTH_LONG).show()
        }
    }


}
