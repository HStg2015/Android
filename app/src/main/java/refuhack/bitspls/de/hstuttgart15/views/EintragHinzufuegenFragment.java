package refuhack.bitspls.de.hstuttgart15.views;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import refuhack.bitspls.de.hstuttgart15.R;

/**
 * Created by Lasse on 06.11.2015.
 */
public class EintragHinzufuegenFragment extends DialogFragment {
    private EditText mEditText;

    public EintragHinzufuegenFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullDIalog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eintrag_hinzufuegen, container);
        return view;
    }
}
