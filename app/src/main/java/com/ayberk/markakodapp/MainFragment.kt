package com.ayberk.markakodapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ayberk.markakodapp.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var isBackPressed = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingss -> {
                val bottomNav =
                    requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                bottomNav.selectedItemId = R.id.settings

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            isBackPressed = true
        }

        // Toolbar'ı aktivite eylem çubuğu olarak ayarla
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        // Başlığı gizle
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        binding. bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false


        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawerlayout)
        val navView = view.findViewById<NavigationView>(R.id.navigationView)
        binding.navigationView.inflateHeaderView(R.layout.navigaiton_baslik)

        // ActionBarDrawerToggle oluşturma
        val actionBarDrawerToggle =
            object : ActionBarDrawerToggle(
                requireActivity(), drawerLayout, binding.toolbar, R.string.Anasayfa, R.string.Profil
            )  {

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                  //  navView_baslik.bringToFront()
                    requireActivity().invalidateOptionsMenu()

                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    requireActivity().invalidateOptionsMenu()
                }
            }

        binding.imgMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.Home -> {

                    drawerLayout.closeDrawers()

                    val bottomNav =
                        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottomNav.selectedItemId = R.id.Home
                    true
                }

             /*   R.id.favorite -> {

                    drawerLayout.closeDrawers()

                    val bottomNav =
                        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottomNav.selectedItemId = R.id.
                    true
                }  */

                R.id.profile -> {
                    // Profile öğesine tıklandığında yapılacak işlemler
                    drawerLayout.closeDrawers()

                    val bottomNav =
                        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottomNav.selectedItemId = R.id.profile
                    true
                }

                R.id.settings -> {
                    // Settings öğesine tıklandığında yapılacak işlemler
                    drawerLayout.closeDrawers()

                    val bottomNav =
                        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
                    bottomNav.selectedItemId = R.id.settings
                    true
                }

                else -> {

                }
            }
                true
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.Home ->

                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.homeFragment)


            /*    R.id.favorite -> {
                    // Profile öğesine tıklandığında yapılacak işlemler
                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.profileFragment)
                } */

                R.id.profile ->
                    childFragmentManager.primaryNavigationFragment?.findNavController()
                        ?.navigate(R.id.profileFragment)


                R.id.settings -> childFragmentManager.primaryNavigationFragment?.findNavController()
                    ?.navigate(R.id.settingsFragment)

                else -> {

                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

