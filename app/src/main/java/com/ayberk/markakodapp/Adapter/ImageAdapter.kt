import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.airbnb.lottie.LottieAnimationView
import com.ayberk.markakodapp.Models.RoomBase
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.Room.DataDao
import com.ayberk.markakodapp.Room.RoomDatabase
import com.bumptech.glide.Glide

class ImageAdapter(
    private val context: Context,
    private val images: List<Int>,
    private val names: List<String>,
    private val prices: List<String>
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private lateinit var db: RoomDatabase
    private lateinit var dataDao: DataDao
    private var roomBaseList: List<RoomBase> = emptyList()
    private val isAnimationPlayedList = MutableList(images.size) { false }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        val name = names[position]
        val price = prices[position]
        holder.bind(image, name, price)

        val animationFav: LottieAnimationView = holder.itemView.findViewById(R.id.animationFavorite)
        val imageLike: ImageView = holder.itemView.findViewById(R.id.imgLike)

        db = Room.databaseBuilder(
            holder.itemView.context.applicationContext,
            RoomDatabase::class.java, "RoomBase"
        )
            .allowMainThreadQueries()
            .build()
        dataDao = db.dataDao()

        roomBaseList = dataDao.getAll()

        val dataExists = roomBaseList.any { it.id == position }

        if (dataExists) {
            // Veritabanında veri varsa, image görüntülenecek
            imageLike.setImageResource(R.drawable.like)
            imageLike.isClickable = false

        } else {
            imageLike.setImageResource(R.drawable.dislike)
            animationFav.cancelAnimation()
            animationFav.visibility = View.INVISIBLE
        }

        imageLike.setOnClickListener {
            val selectedData = RoomBase(
                position,
                images[position].toString(),
                names[position],
                prices[position]
            )

            if (dataExists) {
                // Veritabanında veri varsa, kaldıracak
                dataDao.delete(selectedData)
                Toast.makeText(
                    holder.itemView.context,
                    "Favorilerden Kaldırıldı",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Veritabanında veri yoksa, ekleme yapılacak
                animationFav.playAnimation()
                animationFav.isClickable = false
                animationFav.visibility = View.VISIBLE
                isAnimationPlayedList[position] = true
                dataDao.insert(selectedData)
                Toast.makeText(
                    holder.itemView.context,
                    "Favorilere Eklendi",
                    Toast.LENGTH_SHORT
                ).show()

                // Animasyonun 1 kez çalışıp kaybolması için zamanlayıcı kullanılıyor
                val animationDuration = animationFav.duration
                val animationHandler = android.os.Handler()
                animationHandler.postDelayed({
                    animationFav.visibility = View.INVISIBLE
                }, animationDuration)
            }

            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imgData)
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
