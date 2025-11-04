package com.example.eva.fragmentos;

import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class FragFilterList extends Fragment {
    EditText editText;
    Spinner spinner;
    public interface OnFrgFilterListener{
        void onItemClick(FragFilterList v, String item, int position);
        void onFilterChanged(String filtro, int numRegistros);
    }
}
