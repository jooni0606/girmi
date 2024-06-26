import { Paper, Box, Grid, Typography, Link } from '@mui/material';

function MainFeaturedPost() {
	const post = {
		title: 'Title of a longer featured blog post',
		description:
			"Multiple lines of text that form the lede, informing new readers quickly and efficiently about what's most interesting in this post's contents.",
		image: 'https://source.unsplash.com/random?wallpapers',
		imageText: 'main image description',
		linkText: 'Continue reading…',
	};

	return (
		<Paper
			sx={{
				position: 'relative',
				backgroundColor: 'grey.800',
				color: '#fff',
				mb: 4,
				backgroundSize: 'cover',
				backgroundRepeat: 'no-repeat',
				backgroundPosition: 'center',
				backgroundImage: `url(${post.image})`,
			}}
		>
			<img style={{ display: 'none' }} src={post.image} alt={post.imageText} />
			<Box
				sx={{
					position: 'absolute',
					top: 0,
					bottom: 0,
					right: 0,
					left: 0,
					backgroundColor: 'rgba(0,0,0,.3)',
				}}
			/>
			<Grid container>
				<Grid item md={6}>
					<Box
						sx={{
							position: 'relative',
							p: { xs: 3, md: 6 },
							pr: { md: 0 },
						}}
					>
						<Typography component="h1" variant="h3" color="inherit" gutterBottom>
							{post.title}
						</Typography>
						<Link variant="subtitle1" href="#none">
							{post.linkText}
						</Link>
					</Box>
				</Grid>
			</Grid>
		</Paper>
	);
}
export default MainFeaturedPost;
