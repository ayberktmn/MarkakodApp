import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ayberk.markakodapp.R
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context, private val images: List<Int>, private val names: List<String>, private val prices: List<String>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        val name = names[position]
        val price = prices[position]
        holder.bind(image, name, price)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        private val textName: TextView = itemView.findViewById(R.id.txtName)
        private val textPrice: TextView = itemView.findViewById(R.id.txtPrice)

        fun bind(image: Int, name: String, price: String) {

            Glide.with(context)
                .load(image)
                .into(imageView)
            textName.text = name
            textPrice.text = price
        }
    }
}
