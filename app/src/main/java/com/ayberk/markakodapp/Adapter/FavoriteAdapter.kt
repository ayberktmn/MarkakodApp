import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.airbnb.lottie.LottieAnimationView
import com.ayberk.markakodapp.Models.RoomBase
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.Room.DataDao
import com.ayberk.markakodapp.Room.RoomDatabase
import com.ayberk.markakodapp.databinding.ImageItemBinding
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val favoriteData: ArrayList<RoomBase>,
    private val imageList: List<Int> // Resimlerin olduğu liste
) : RecyclerView.Adapter<FavoriteAdapter.ImageViewHolder>() {

    private lateinit var db: RoomDatabase
    private lateinit var dataDao: DataDao
    private val isAnimationPlayedList = MutableList(favoriteData.size) { false }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = favoriteData[position]
        holder.bind(currentItem, imageList[position])

        val animationFav: LottieAnimationView = holder.binding.animationFavorite

        db = Room.databaseBuilder(
            holder.binding.root.context,
            RoomDatabase::class.java,
            "RoomBase"
        )
            .allowMainThreadQueries()
            .build()
        dataDao = db.dataDao()

        animationFav.setOnClickListener {
            dataDao.delete(currentItem)
            Toast.makeText(
                holder.itemView.context,
                "Favorilerden Silindi",
                Toast.LENGTH_SHORT
            ).show()

            favoriteData.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, favoriteData.size)

            holder.itemView.findNavController().navigate(R.id.action_favoriteFragment_self)
        }

        holder.binding.imgLike.setOnClickListener {
            val favoriFood = favoriteData[position]
            dataDao.delete(favoriFood)
            Toast.makeText(holder.itemView.context, "Favorilerden Silindi", Toast.LENGTH_SHORT)
                .show()

            favoriteData.removeAt(position)
            notifyItemRemoved(position)

            holder.itemView.findNavController().navigate(R.id.action_favoriteFragment_self)
        }
    }

    override fun getItemCount(): Int {
        return favoriteData.size
    }

    inner class ImageViewHolder(val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RoomBase, image: Int) {
            Glide.with(binding.root)
                .load(imageList[item.id])
                .into(binding.imgData)
            binding.txtName.text = item.names
            binding.txtPrice.text = item.prices
        }
    }
}
