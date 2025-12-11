package com.example.eva;

import android.widget.ImageView;
import android.widget.TextView;

class Adaptador extends ArrayAdapter<Contacto> {
    public static class ViewHolder {
        ImageView foto, mute;
        TextView tvNombre;
    }

    private Context contexto;
    private ArrayList<Contacto> datos;

    public Adaptador(Context contexto, ArrayList<Contacto> datos) {
        super(contexto, R.layout.filacontacto, datos);
        this.contexto = contexto;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Contacto contacto = (Contacto) datos.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(contexto).inflate(R.layout.filacontacto, null);
            viewHolder = new ViewHolder();
            viewHolder.foto = (ImageView) convertView.findViewById(R.id.foto);
            viewHolder.tvNombre = (TextView) convertView.findViewById(R.id.nombre);
            viewHolder.mute = (ImageView) convertView.findViewById(R.id.mute);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        String strNombre = contacto.getId() + ".jpg";

        viewHolder.tvNombre.setText(contacto.toString());

        if (contacto.isMute())
            viewHolder.mute.setImageResource(R.drawable.mute);
        else
            viewHolder.mute.setImageDrawable(null);
        return convertView;
    }
}

