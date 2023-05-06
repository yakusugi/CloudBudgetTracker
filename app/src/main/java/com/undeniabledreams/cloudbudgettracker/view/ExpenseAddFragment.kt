package com.undeniabledreams.cloudbudgettracker.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.undeniabledreams.cloudbudgettracker.R
import com.undeniabledreams.cloudbudgettracker.core.*
import com.undeniabledreams.cloudbudgettracker.dao.BudgetTrackerExpenseInsertDao
import com.undeniabledreams.cloudbudgettracker.dao.BudgetTrackerExpenseSelectDao
import com.undeniabledreams.cloudbudgettracker.dao.BudgetTrackerUserDao
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

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateEd = view.findViewById(R.id.edit_expense_date_id)
        val context: Context? = context?.applicationContext

        val myCalender = Calendar.getInstance()
//        val datePicker = DatePickerDialog.OnDateSetListener {
//                view, year, month, dayOfMonth ->
//            myCalender.set(Calendar.YEAR, year)
//            myCalender.set(Calendar.MONTH, month)
//            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            updateLabel(myCalender)
//        }

        dateEd.setOnClickListener {
            val datePicker =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    // Set the selected date to the EditText
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    dateEd.setText(selectedDate)
                }

            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(requireContext(), datePicker, year, month, dayOfMonth)
            datePickerDialog.show()
        }



        storeEd = view.findViewById(R.id.edit_expense_store_id)
        productNameEd = view.findViewById(R.id.edit_expense_product_name_id)
        productTypeEd = view.findViewById(R.id.edit_expense_product_type_id)
        priceEd = view.findViewById(R.id.edit_expense_price_id)
        vatEd = view.findViewById(R.id.edit_expense_vat_id)
        currencyEd = view.findViewById(R.id.auto_currency_complete_view)
        val budgetTrackerExpenseSelectDao = context?.let { BudgetTrackerExpenseSelectDao(it) }
        val currencyDto = CurrencyDto()

        currencyEd.setOnClickListener {
            if (budgetTrackerExpenseSelectDao != null) {
                val currencies: List<CurrencyDto> =
                    budgetTrackerExpenseSelectDao.selectFromCurrency(currencyDto)


                // Create a list to hold the data retrieved from the database
                val currencyList = mutableListOf<String>()

                // Iterate through the result set and add each expense to the list
                for (currency in currencies) {
                    val currencyName = currency.getCurrency()
                    val currencyNameAbbr = currency.getCurrencyAbbr()

                    // Concatenate the expense details into a string and add it to the list
                    val currencyString = "$currencyName: $currencyNameAbbr"
                    currencyList.add(currencyString)

                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, currencyList)

                    // Get a reference to the AutoCompleteTextView in the layout
                    val autoCurrencyCompleteView = view.findViewById<AutoCompleteTextView>(R.id.auto_currency_complete_view)

                    // Set the adapter for the AutoCompleteTextView
                    autoCurrencyCompleteView.setAdapter(adapter)
                }
            }
        }

    }


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