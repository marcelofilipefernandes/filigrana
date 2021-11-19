package com.pm.filigrana.fragments.product

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pm.filigrana.R
import com.pm.filigrana.data.entities.Product
import com.pm.filigrana.data.viewmodel.ProductViewModel
import com.pm.filigrana.utils.Utils.Companion.hideKeyboard
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mProductViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        view.updateProductName.setText(args.currentProduct.name)

        setHasOptionsMenu(true)

        return view;
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update_delete_product, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        hideKeyboard()

        if (item.itemId == R.id.menu_update) {
            updateProduct()
        }

        if (item.itemId == R.id.menu_delete) {
            deleteProduct()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun updateProduct() {
        if (!isValid()) {
            return Toast.makeText(
                requireContext(),
                getString(R.string.empty_product_name),
                Toast.LENGTH_LONG
            ).show()
        }
        val product = Product(args.currentProduct.id, updateProductName.text.toString(), "")

        mProductViewModel.updateProduct(product)

        Toast.makeText(
            requireContext(),
            getString(R.string.product_successfully_updated),
            Toast.LENGTH_LONG
        ).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun deleteProduct() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            mProductViewModel.deleteProduct(args.currentProduct)
            Toast.makeText(
                requireContext(),
                getString(R.string.product_successfully_deleted),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(getString(R.string.delete))
        builder.setMessage(getString(R.string.question_delete))
        builder.create().show()
    }

    private fun isValid(): Boolean {
        return !TextUtils.isEmpty(updateProductName.text.toString())
    }
}