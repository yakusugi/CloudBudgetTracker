package com.undeniabledreams.cloudbudgettracker.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.undeniabledreams.cloudbudgettracker.R
import com.undeniabledreams.cloudbudgettracker.core.BudgetTrackerMasterDto
import com.undeniabledreams.cloudbudgettracker.core.ProductTypeDto
import com.undeniabledreams.cloudbudgettracker.core.StoreDto
import com.undeniabledreams.cloudbudgettracker.dao.BudgetTrackerExpenseInsertDao
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExpenseAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExpenseAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var dateEd: EditText
    lateinit var storeEd: EditText
    lateinit var productNameEd: EditText
    lateinit var productTypeEd: EditText
    lateinit var priceEd: EditText
    lateinit var vatEd: EditText
    lateinit var currencyEd: EditText
    lateinit var budgetAddButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateEd = view.findViewById(R.id.edit_expense_date_id)

        val myCalender = Calendar.getInstance()
//        val datePicker = DatePickerDialog.OnDateSetListener {
//                view, year, month, dayOfMonth ->
//            myCalender.set(Calendar.YEAR, year)
//            myCalender.set(Calendar.MONTH, month)
//            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            updateLabel(myCalender)
//        }

        dateEd.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Set the selected date to the EditText
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                dateEd.setText(selectedDate)
            }

            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), datePicker, year, month, dayOfMonth)
            datePickerDialog.show()
        }



        storeEd = view.findViewById(R.id.edit_expense_store_id)
        productNameEd = view.findViewById(R.id.edit_expense_product_name_id)
        productTypeEd = view.findViewById(R.id.edit_expense_product_type_id)
        priceEd = view.findViewById(R.id.edit_expense_price_id)
        vatEd = view.findViewById(R.id.edit_expense_vat_id)
        currencyEd = view.findViewById(R.id.edit_expense_currency_id)
        budgetAddButton = view.findViewById(R.id.budget_add_btn)

        budgetAddButton.setOnClickListener {

            val dateString: String = dateEd.text.toString()
            val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd")
            val date: Date = dateFormat.parse(dateString)
            val store: String = storeEd.text.toString()
            val productName: String = productNameEd.text.toString()
            val productType: String = productTypeEd.text.toString()
            val priceString: String = priceEd.text.toString()
            val price: Double = priceString.toDouble()
            val vatString: String = vatEd.text.toString()
            val vat: Double = vatString.toDouble()
            val currencyString: String = currencyEd.text.toString()
            val currency: Int = currencyString.toInt()
            val budgetTrackerMasterDto = BudgetTrackerMasterDto()
            val storeDto = StoreDto()
            val productTypeDto = ProductTypeDto()
            budgetTrackerMasterDto.setDate(date)
            storeDto.setStoreName(store)
            budgetTrackerMasterDto.setProductName(productName)
            productTypeDto.setProductTypeName(productType)
            budgetTrackerMasterDto.setPrice(price)
            budgetTrackerMasterDto.setVat(vat)
            budgetTrackerMasterDto.setCurrencyId(currency)

            try {
                context?.let { BudgetTrackerExpenseInsertDao(it) }
                    ?.insertIntoMaster(productTypeDto, storeDto, budgetTrackerMasterDto)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

//    private fun updateLabel(myCalendar: Calendar) {
//        val myFormat = "dd-MM-yyyy"
//        val sdf = SimpleDateFormat(myFormat, Locale.US)
//        dateEd.setText(sdf.format(myCalendar.time))
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExpenseAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExpenseAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}