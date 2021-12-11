package com.example.snapshots

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.snapshots.databinding.FragmentAddBinding
import java.lang.ref.PhantomReference

/
class AddFragment : Fragment() {

    private val RC_GELLERY=18
    private val PHAT SNAPSHOT="snaphots"
    private lateinit var mBinding:FragmentAddBinding
    private lateinit var mStorageReferences: StorageReference
    private lateinit var mDatabasesReference: DatabaseReference

    private var mPhotoSelectedUrl:null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentAddBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnPost.setOnClickListener{postSnapshot()}
        mBinding.btnSelect.setOnClickListener{openGallery()}

        mStorageReferences=FirebaseStorage.getInstance().reference
        mDatabasesReference=FirebaseDatase.getInstance().reference.child(PATH_SNAPSHOT)
    }
    private fun openGallery(){
        val intent=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, RC_GELLERY)
    }
    private fun postSnapshot(){

    }
private fun saveSnapshot(){

}

    override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK){
            if (requestCode== RC_GELLERY){
                mPhotoSelectedUrl= data?.data
                mBinding.imgPhoto.setImage URI
            }
        }
    }
}