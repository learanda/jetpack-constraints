package com.example.jetpackcomposecatalogo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ConstraintExample1() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        // createRefs es una función de la librería y nos permite crear referencias como si fuesen IDs
        val (boxRed, boxBlue, boxYellow, boxMagenta, boxGreen) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })
        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta) {
                bottom.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                top.linkTo(boxRed.bottom)
                end.linkTo(boxRed.start)
            })
    }
}

@Composable
fun ConstraintExampleGuide() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        // val startGuide = createGuidelineFromTop(16.dp)

        val boxRed = createRef()
        val topGuide = createGuidelineFromTop(0.1f)
        val startGuide = createGuidelineFromStart(0.25f)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxGreen, boxYellow) = createRefs()
        val barrier = createEndBarrier(boxRed, boxGreen)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start, margin = 16.dp)
            })

        Box(modifier = Modifier
            .size(235.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(boxGreen.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Yellow)
                .constrainAs(boxYellow) {
                    start.linkTo(barrier)
                })
    }
}

@Preview
@Composable
fun ConstraintChainExample() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (boxRed, boxGreen, boxYellow) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start)
                end.linkTo(boxRed.start)
            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(boxGreen.end)
                end.linkTo(boxYellow.start)
            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxRed.end)
                end.linkTo(parent.end)
            })

        // Este es como el que viene por defecto
        createHorizontalChain(boxGreen, boxRed, boxYellow, chainStyle = ChainStyle.Spread)
        //createHorizontalChain(boxGreen, boxRed, boxYellow, chainStyle = ChainStyle.Packed)
        //createHorizontalChain(boxGreen, boxRed, boxYellow, chainStyle = ChainStyle.SpreadInside)

        // También está el createVerticalChain pero para eso tengo que tener enganchados los Box con top y bottom
    }
}