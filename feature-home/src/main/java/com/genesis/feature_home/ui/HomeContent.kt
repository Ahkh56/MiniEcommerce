package com.genesis.feature_home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.genesis.domain.model.Product
import com.genesis.feature_cart.db.entity.CartItem
import com.genesis.feature_cart.vm.CartViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeContent(products: List<Product>, navController: NavController) {
    val sliderProducts = products.take(5)
    val categorizedProducts = products.groupBy { it.category }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                ImageSlider(sliderProducts)
            }
            categorizedProducts.forEach { (category, products) ->
                item {
                    CategoryRow(
                        category = category,
                        products = products,
                        onViewAllClick = {
                            navController.navigate("category/$category")
                        },
                        onAddToCartClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Item added to cart successfully")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryRow(
    category: String,
    products: List<Product>,
    onViewAllClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = category, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "View All",
                modifier = Modifier.clickable { onViewAllClick() },
                color = MaterialTheme.colorScheme.primary
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products) { product ->
                ProductItem(product = product, onAddToCartClick = onAddToCartClick)
            }
        }
    }
}

@Composable
fun ImageSlider(products: List<Product>) {
    val pagerState = rememberPagerState(pageCount = { products.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % products.size)
        }
    }

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(products[page].image),
                contentDescription = products[page].title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    cartViewModel: CartViewModel = koinViewModel(),
    onAddToCartClick: () -> Unit
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val cartItem = cartItems.find { it.id == product.id }
    val quantity = cartItem?.quantity ?: 0

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.height(48.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${String.format("%.2f", product.price * (if (quantity > 0) quantity else 1))}")
            Spacer(modifier = Modifier.weight(1f))
            if (quantity == 0) {
                Button(onClick = {
                    val newCartItem = CartItem(
                        id = product.id,
                        title = product.title,
                        price = product.price,
                        image = product.image,
                        quantity = 1
                    )
                    cartViewModel.addToCart(newCartItem)
                    onAddToCartClick()
                }) {
                    Text(text = "Add to Cart")
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        if (cartItem != null) {
                            cartViewModel.decreaseQuantity(cartItem)
                        }
                    }) {
                        Icon(Icons.Filled.Remove, contentDescription = "Remove")
                    }
                    Text(text = quantity.toString())
                    IconButton(onClick = {
                        if (cartItem != null) {
                            cartViewModel.increaseQuantity(cartItem)
                        }
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            }
        }
    }
}
